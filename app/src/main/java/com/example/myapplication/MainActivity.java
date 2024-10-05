package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText resultDisplay;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultDisplay = findViewById(R.id.result_display);


        setupButtons();
    }

    private void setupButtons() {
        findViewById(R.id.button_clear).setOnClickListener(v -> clear());
        findViewById(R.id.button_7).setOnClickListener(v -> appendToDisplay("7"));
        findViewById(R.id.button_8).setOnClickListener(v -> appendToDisplay("8"));
        findViewById(R.id.button_9).setOnClickListener(v -> appendToDisplay("9"));
        findViewById(R.id.button_divide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.button_4).setOnClickListener(v -> appendToDisplay("4"));
        findViewById(R.id.button_5).setOnClickListener(v -> appendToDisplay("5"));
        findViewById(R.id.button_6).setOnClickListener(v -> appendToDisplay("6"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.button_1).setOnClickListener(v -> appendToDisplay("1"));
        findViewById(R.id.button_2).setOnClickListener(v -> appendToDisplay("2"));
        findViewById(R.id.button_3).setOnClickListener(v -> appendToDisplay("3"));
        findViewById(R.id.button_subtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.button_0).setOnClickListener(v -> appendToDisplay("0"));
        findViewById(R.id.button_decimal).setOnClickListener(v -> appendToDisplay("."));
        findViewById(R.id.button_equals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.button_parentheses).setOnClickListener(v -> appendToDisplay("()"));
        findViewById(R.id.button_percent).setOnClickListener(v -> setOperator("%"));
        findViewById(R.id.button_add).setOnClickListener(v -> setOperator("+"));
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        resultDisplay.setText("");
    }

    private void appendToDisplay(String value) {
        currentInput += value;
        resultDisplay.setText(currentInput);
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        resultDisplay.setText("Error");
                        return;
                    }
                    break;
                case "%":
                    result = firstOperand % secondOperand;
                    break;
            }

            currentInput = String.valueOf(result);
            resultDisplay.setText(currentInput);
            operator = "";
        }
    }
}
