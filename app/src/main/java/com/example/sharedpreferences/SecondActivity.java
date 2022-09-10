package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String current_color_name;      // to store the color name send by MainActivity
    private int current_count;      // to store the counter value send by MainActivity
    private EditText countValueUpdEdtxt;       // to get the new value from the user
    private TextView printColorTxt;        // to show the current color
    private TextView printCountTxt;          // to show the current counter value
    private String shared_pref_file_name;     // to store the shared Preference file name
    SharedPreferences sharedPreferences;          // shared Preference reference to initiate it with file name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // find the edittext from the view
        countValueUpdEdtxt = findViewById(R.id.countEditText);

        Spinner spinner = (Spinner) findViewById(R.id.color_selector);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                                                            R.array.color_names,
                                                                            android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // set onClickListener
        spinner.setOnItemSelectedListener(this);

        // get the data from the intent
        Intent intent = getIntent();
        current_color_name = intent.getStringExtra(MainActivity.COLOR_EXTRA_KEY);
        current_count = intent.getIntExtra(MainActivity.COUNTER_EXTRA_KEY,0);
        shared_pref_file_name = intent.getStringExtra(MainActivity.SHAREDPREF_EXTRA_KEY);

        // getting the shared preference file
        sharedPreferences = getSharedPreferences(shared_pref_file_name,MODE_PRIVATE);

        // find the textViews to show color name and count
        printColorTxt = findViewById(R.id.currentColorTxt);
        printCountTxt = findViewById(R.id.currentCountTxt);

        // pass the values to display
        printColorTxt.setText(current_color_name);
        printCountTxt.setText(String.valueOf(current_count));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String update_color_name = (String) parent.getItemAtPosition(position);  // get the selected color
        // get the color id with the color name
        int update_color_id = getResources().getIdentifier(update_color_name,"color",getPackageName());
        int update_color = getResources().getColor(update_color_id);
        // now start the editor
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        // put the new user choosen color
        sharedPrefEditor.putInt(getString(R.string.colorUpdateKey),update_color);
        sharedPrefEditor.apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // save the current color and counter value
    public void saveWithoutChangePref(View view) {
        // get the color id with the color name
        int current_color_id = getResources().getIdentifier(current_color_name,"color",getPackageName());
        int current_color = getResources().getColor(current_color_id);
        // now start the editor
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        // put the same color which is at background right now
        sharedPrefEditor.putInt(getString(R.string.colorUpdateKey),current_color);
        // put the same counter value
        sharedPrefEditor.putInt(getString(R.string.counterUpdateKey),current_count);
        sharedPrefEditor.apply();
    }

    // delete the preference file
    public void resetPreference(View view) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.clear();
        prefEditor.apply();
    }

    // here only counter value is change
    // color is automatically save in preference if user user the spinner
    public void updatePreferences(View view) {
        String upd_count_value_str = countValueUpdEdtxt.getText().toString();
        int upd_count = Integer.valueOf(upd_count_value_str);          // update the counter value

        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();  // get the editor object to change the Shared preference file
        sharedPrefEditor.putInt(getString(R.string.counterUpdateKey),upd_count);
        sharedPrefEditor.apply();
    }
}