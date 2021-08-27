package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calcu extends AppCompatActivity {

    TextView resultTV;
    EditText fistET,secondET;
    Button plusbutton,minusbutton,mulbutton,dividebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu);

        resultTV = findViewById(R.id.cal_display_result);
        fistET = findViewById(R.id.cal_first);
        secondET = findViewById(R.id.cal_second);
        plusbutton = findViewById(R.id.cal_plus_button);
        minusbutton = findViewById(R.id.cal_minus_button);
        mulbutton =findViewById(R.id.cal_mult_button);
        dividebutton = findViewById(R.id.cal_div_button);

        plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fist = Integer.parseInt(fistET.getText().toString());
                int second = Integer.parseInt(secondET.getText().toString());
                int result = fist + second;

                resultTV.setText(""+result);

                Toast.makeText(getApplicationContext(),"Result is: "+result,Toast.LENGTH_SHORT);
            }
        });

        minusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fist = Integer.parseInt(fistET.getText().toString());
                int second = Integer.parseInt(secondET.getText().toString());
                int result = fist - second;

                resultTV.setText(""+result);

                Toast.makeText(getApplicationContext(),"Result is: "+result,Toast.LENGTH_SHORT);
            }
        });

        mulbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fist = Integer.parseInt(fistET.getText().toString());
                int second = Integer.parseInt(secondET.getText().toString());
                int result = fist * second;

                resultTV.setText(""+result);

                Toast.makeText(getApplicationContext(),"Result is: "+result,Toast.LENGTH_SHORT);
            }
        });

        dividebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fist = Integer.parseInt(fistET.getText().toString());
                int second = Integer.parseInt(secondET.getText().toString());
                int result = fist + second;

                resultTV.setText(""+result);

                Toast.makeText(getApplicationContext(),"Result is: "+result,Toast.LENGTH_SHORT);
            }
        });

    }
}
