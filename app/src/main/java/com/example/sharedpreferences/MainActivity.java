package com.example.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextview;
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile;
    private int counter = 0;
    private String current_color = "gray";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextview = findViewById(R.id.counter_textview);

        // it can be any string but conventially it is more better to use app name
        sharedPrefFile = getResources().getString(R.string.prefrence_file_name);
        // let's create a file with the app name. private for security purposes
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // get the saved data back
        counter = sharedPreferences.getInt("currentValue", 0);        // second parameter is the default value
        int color = sharedPreferences.getInt("currentColor", getResources().
                getColor(R.color.gray));

        counterTextview.setText(String.valueOf(counter));
        counterTextview.setBackgroundColor(color);
    }

    public void changeBackground(View view) {
        // finding which button is clicked
        switch (view.getId()) {
            case R.id.black_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.black));
                        current_color = "black";
                break;

            case R.id.red_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.red));
                        current_color = "red";
                break;

            case R.id.blue_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.blue));
                        current_color = "blue";
                break;

            case R.id.green_button:
                counterTextview.setBackgroundColor(getResources().
                        getColor(R.color.green));
                        current_color = "green";
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
        // also clear the SharedPreference
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.clear();
        prefEditor.apply();
    }

    // shared Preference File can be written in this method
    @Override
    protected void onPause() {
        super.onPause();
        // only editor can write to the Shared Preference file
        SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();
        // get the color
        ColorDrawable colorDrawable = (ColorDrawable) counterTextview.getBackground();
        int color = colorDrawable.getColor();
        // put the key-value pairs inside the Shared Preference File
        preferenceEditor.putInt("currentColor", color);
        preferenceEditor.putInt("currentValue", counter);
        // save the data
        preferenceEditor.apply();
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        String preference_file = getResources().getString(R.string.prefrence_file_name);
        intent.putExtra("file_name",preference_file);
        intent.putExtra("current_color_name",current_color);
        intent.putExtra("current_count",counter);
        startActivity(intent);
    }
}