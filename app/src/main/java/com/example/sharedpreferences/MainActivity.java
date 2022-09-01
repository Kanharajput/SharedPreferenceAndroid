package com.example.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextview;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextview = findViewById(R.id.counter_textview);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counterValue");
            int color = savedInstanceState.getInt("currentColor");
            counterTextview.setText(String.valueOf(counter));
            counterTextview.setBackgroundColor(color);
        }
    }

    public void changeBackground(View view) {
        // finding which button is clicked
        switch (view.getId()) {
            case R.id.black_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.black));
                break;

            case R.id.red_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.red));
                break;

            case R.id.blue_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.blue));
                break;

            case R.id.green_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.green));
                break;
        }
    }

    public void countUp(View view) {
        counter++;
        counterTextview.setText(String.valueOf(counter));
    }

    public void resetAll(View view) {
        // reset the counter
        counter = 0;
        counterTextview.setText(String.valueOf(counter));
        // reset the color
        counterTextview.setBackgroundColor(getResources().
                                            getColor(R.color.gray));
    }

    @Override  // save the current color and current counter value
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // can't directly get the color
        ColorDrawable colorDrawable = (ColorDrawable) counterTextview.getBackground();
        int color = colorDrawable.getColor();

        outState.putInt("currentColor", color);
        outState.putInt("counterValue", counter);
        super.onSaveInstanceState(outState);
    }
}