package ch.zli.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button increment;
    private Button subtract;
    private Button reset;
    private TextView counterView;
    private int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        increment = findViewById(R.id.plusOne);
        counterView = findViewById(R.id.counter);
        subtract = findViewById(R.id.minusOne);
        reset = findViewById(R.id.reset);

        increment.setOnClickListener(v -> {
            counter++;
            counterView.setText(String.valueOf(counter));
        });

        subtract.setOnClickListener(v -> {
            counter--;
            counterView.setText(String.valueOf(counter));
        });

        reset.setOnClickListener(v -> {
            counter = 0;
            counterView.setText(String.valueOf(counter));
        });
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter");
            counterView.setText(counter);
        }
    }

    @Override
    protected void onSaveInstanceState (Bundle outState){
       super.onSaveInstanceState(outState);
       outState.putInt("counter", counter);
    }
}