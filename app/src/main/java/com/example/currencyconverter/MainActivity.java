package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    Spinner spinnerFrom, spinnerTo;
    Button btnConvert, btnSettings;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnConvert = findViewById(R.id.btnConvert);
        btnSettings = findViewById(R.id.btnSettings);
        tvResult = findViewById(R.id.tvResult);

        btnConvert.setOnClickListener(view -> convertCurrency());

        btnSettings.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    private void convertCurrency() {
        String amountStr = etAmount.getText().toString();
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        String from = spinnerFrom.getSelectedItem().toString();
        String to = spinnerTo.getSelectedItem().toString();

        double rateFrom = getRate(from);
        double rateTo = getRate(to);

        double result = (amount / rateFrom) * rateTo;
        tvResult.setText(String.format("%.2f %s", result, to));
    }

    private double getRate(String currency) {
        switch (currency) {
            case "INR": return 92.36;
            case "EUR": return 0.86;
            case "JPY": return 158.46;
            case "USD": return 1.0;
            default: return 1.0;
        }
    }
}

