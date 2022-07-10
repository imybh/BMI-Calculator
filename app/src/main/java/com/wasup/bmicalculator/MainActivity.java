package com.wasup.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private TextView resultText;
    private RadioButton malebtn;
    private RadioButton femalebtn;
    private EditText agetext;
    private EditText feettext;
    private EditText inchestext;
    private EditText weighttext;
    private Button calculatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onclickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.tv_result);
        malebtn = findViewById(R.id.rb_m);
        femalebtn = findViewById(R.id.rb_f);
        agetext = findViewById(R.id.et_age);
        feettext = findViewById(R.id.et_feet);
        inchestext = findViewById(R.id.et_inch);
        weighttext = findViewById(R.id.et_kg);
        calculatebtn = findViewById(R.id.bt_cal);
    }

    private void onclickListener(){
        calculatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              double bmiresult =  calculateBMI();

                String agetextv =  agetext.getText().toString();
                int age = Integer.parseInt(agetextv);

                if(age >= 18){
                    displayResult(bmiresult);
                } else {
                    displayGuidance(bmiresult);
                }


            }
        });
    }


    private double calculateBMI() {

      String feettextv =  feettext.getText().toString();
      String inchestextv =  inchestext.getText().toString();
      String weighttextv =  weighttext.getText().toString();

      // Converting The Number 'Strings' into 'int' Variables
      int feet = Integer.parseInt(feettextv);
      int inches = Integer.parseInt(inchestextv);
      int weight = Integer.parseInt(weighttextv);

      int totalinches = (feet * 12) + inches;
      double heightInMeters = totalinches * 0.0254;
      // BMI Formula = weight/height in mts
      return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat mydecimalformatter = new DecimalFormat("0.00");
        String bmitextresult = mydecimalformatter.format(bmi);

        String fullResultString;
        if (bmi < 18.5) {
            //Display Underweight
            fullResultString = bmitextresult + " - You Are Underweight";
        } else if (bmi > 25) {
            //Display Healthy
            fullResultString = bmitextresult + " - You Are Overweight";
        } else{
            //Display Healthy
            fullResultString = bmitextresult + " - You Are Healthy ! Thumbs Up";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat mydecimalformatter = new DecimalFormat("0.00");
        String bmitextresult = mydecimalformatter.format(bmi);

        String fullResultString;
        if (malebtn.isChecked()) {
            //Display Boy Guidance
            fullResultString = bmitextresult + " - As You Are Under 18 , Please Consult Your Doctor For The Healthy Range For Boys";
        } else if (femalebtn.isChecked()) {
            //Display Female Guidance
            fullResultString = bmitextresult + " - As You Are Under 18 , Please Consult Your Doctor For The Healthy Range For Girls";
        } else {
            //Display General Guidance
            fullResultString = bmitextresult + " - As You Are Under 18 , Please Consult Your Doctor For The Healthy Range";
        }
        resultText.setText(fullResultString);
    }
}