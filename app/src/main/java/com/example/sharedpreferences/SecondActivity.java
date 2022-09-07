package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String color_name;
    private int current_count;
    private TextView printColorTxt;
    private TextView printCountTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Spinner spinner = (Spinner) findViewById(R.id.color_selector);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                                                            R.array.color_names,
                                                                            android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // get the data from the intent
        Intent intent = getIntent();
        color_name = intent.getStringExtra("current_color_name");
        current_count = intent.getIntExtra("current_count",0);

        // find the textViews to show color name and count
        printColorTxt = findViewById(R.id.currentColorTxt);
        printCountTxt = findViewById(R.id.currentCountTxt);

        // pass the values to display
        printColorTxt.setText(color_name);
        printCountTxt.setText(String.valueOf(current_count));
        Log.d("COLOR",color_name);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}