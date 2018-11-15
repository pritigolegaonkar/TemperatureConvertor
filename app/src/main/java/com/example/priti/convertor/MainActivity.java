package com.example.priti.convertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView listView;
    Button convertButton;
    TextView userInput, userOutput;
    RadioGroup tempConvertGroup;
    int fToc = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = (TextView) findViewById(R.id.userInput);
        userOutput = (TextView) findViewById(R.id.userOutput);
        tempConvertGroup = (RadioGroup) findViewById(R.id.tempConvertGroup);
        tempConvertGroup.check(R.id.ftoc);

        tempConvertGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.ftoc) {
                    fToc = 1;
                } else if (i == R.id.ctof) {
                    fToc = 0;
                }
            }
        });

        convertButton = (Button) findViewById(R.id.convertButton);
        listView = (TextView) findViewById(R.id.listView);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userInput.getText().toString().trim().length() > 0){
                    double given_temp = Double.parseDouble(userInput.getText().toString());
                    if (fToc == 1) {
                        double result_temp = (given_temp - 32) / 1.8;
                        given_temp = round(given_temp, 1);
                        result_temp = round(result_temp, 1);
                        userInput.setText(given_temp + "");
                        userOutput.setText(result_temp + "");

                        listView.setMovementMethod(new ScrollingMovementMethod());
                        String histData = listView.getText().toString();
                        listView.setText(histData + "\n" + "\tF to C: " + given_temp + " -> " + result_temp);
                    } else if (fToc == 0) {
                        double result_temp = (given_temp * 1.8) + 32;
                        given_temp = round(given_temp, 1);
                        result_temp = round(result_temp, 1);
                        userInput.setText(given_temp + "");
                        userOutput.setText(result_temp + "");
                        listView.setMovementMethod(new ScrollingMovementMethod());
                        String histData = listView.getText().toString();
                        listView.setText(histData + "\n" + "\tC to F: " + given_temp + " -> " + result_temp);
                    }
                }
                else {
                    userOutput.setText("");
                    Toast.makeText(MainActivity.this,"Please enter input value", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("histData", listView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listView.setText(savedInstanceState.getString("histData"));
    }
}




