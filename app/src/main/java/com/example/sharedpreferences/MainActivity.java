package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextview;
    private SharedPreferences sharedPreferences;         // shared Preference object
    private int counter = 0;
    private String current_color = "gray";
    public static final String COLOR_EXTRA_KEY = "color";
    public static final String COUNTER_EXTRA_KEY = "counter";
    public static final String SHAREDPREF_EXTRA_KEY = "shared pref file";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextview = findViewById(R.id.counter_textview);
        // let's create a file with the app name. private for security purposes
        sharedPreferences = getSharedPreferences(getString(R.string.prefrence_file_name), MODE_PRIVATE);

        // get the saved data back
        counter = sharedPreferences.getInt(COUNTER_EXTRA_KEY, 0);        // second parameter is the default value
        int color = sharedPreferences.getInt(COLOR_EXTRA_KEY, getResources().
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

    // update the counter value
    public void countUp(View view) {
        counter++;
        counterTextview.setText(String.valueOf(counter));
    }

    // delete the preference file
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

    // this intent opens the secondActivity and pass the preference file name
    public void openSecondActivity(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(SHAREDPREF_EXTRA_KEY,getString(R.string.prefrence_file_name));
        intent.putExtra(COLOR_EXTRA_KEY,current_color);
        intent.putExtra(COUNTER_EXTRA_KEY,counter);
        startActivity(intent);
    }

    // this method will call by system automatically when
    // we return back to mainActivity from secondActivity
    @Override
    protected void onStart() {
        super.onStart();
        // open the preference file, it will not create a new file because there is already a preference file with this name
        sharedPreferences = getSharedPreferences(getString(R.string.prefrence_file_name), MODE_PRIVATE);

        // get the saved data back
        counter = sharedPreferences.getInt(getString(R.string.counterUpdateKey), 0);        // second parameter is the default value
        int color = sharedPreferences.getInt(getString(R.string.colorUpdateKey),
                                                getResources().getColor(R.color.gray));

        counterTextview.setText(String.valueOf(counter));
        counterTextview.setBackgroundColor(color);
    }
}