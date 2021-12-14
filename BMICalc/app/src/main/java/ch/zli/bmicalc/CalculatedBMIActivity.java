package ch.zli.bmicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatedBMIActivity extends AppCompatActivity {

    private TextView bmi_result;
    private double weight;
    private double height;
    private double result;
    private TextView classification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        weight = Double.parseDouble(intent.getStringExtra(MainActivity.EXTRA_WEIGHT));
        height = Double.parseDouble(intent.getStringExtra(MainActivity.EXTRA_HEIGHT));
        bmi_result = findViewById(R.id.bmi_result);
        classification = findViewById(R.id.classification);

        result = weight/(height * height);
        bmi_result.setText(String.valueOf(result));

        classification(result);
    }

    public void classification(double result){
        if (result < 18.5) {
            classification.setText("underweight");
        } else if (result > 18.5 && result < 25) {
            classification.setText("Normal weight");
        }else if (result > 25 && result < 30) {
            classification.setText("Pre-obesity");
        }else if (result > 30 && result < 35) {
            classification.setText("Obesity class I");
        }else if (result > 35 && result < 40) {
            classification.setText("Obesity class II");
        }else if (result > 40) {
            classification.setText("Obesity class III");
        }
    }
}