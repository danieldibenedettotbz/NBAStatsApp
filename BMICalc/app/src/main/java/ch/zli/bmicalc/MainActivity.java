package ch.zli.bmicalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_WEIGHT = "ch.zli.bmicalc.WEIGHT";
    public final static String EXTRA_HEIGHT = "ch.zli.bmicalc.HEIGHT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateBMI(View view){
        Intent intent = new Intent(this, CalculatedBMIActivity.class);

        EditText weightInput = (EditText) findViewById(R.id.weight_input);
        String weight = weightInput.getText().toString();

        EditText heightInput = (EditText) findViewById(R.id.height_input);
        String height = heightInput.getText().toString();

        intent.putExtra(EXTRA_WEIGHT, weight);
        intent.putExtra(EXTRA_HEIGHT, height);
        startActivity(intent);
    }
}