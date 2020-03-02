package com.example.bmicalc;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private BMICalc model;
    private EditText height, weight;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupFAB();
        setupViews();
        instantiateFields();

    }

    private void instantiateFields() {
        model = new BMICalc();
        decimalFormat = new DecimalFormat("00.00");
    }

    private void setupViews() {
        height = findViewById(R.id.main_height_et);
        weight = findViewById(R.id.main_weight_et);
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleFABClick(view);
            }
        });
    }

    private void handleFABClick(View view) {
        String strHeight = height.getText().toString();
        String strWeight = weight.getText().toString();

        if (strHeight.equals("") || strWeight.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Height and weight cannot be empty",
                    Toast.LENGTH_LONG)
                    .show();
        } else {
            showBMIResults(view, strHeight, strWeight);
        }
    }

    private void showBMIResults(View view, String strHeight, String strWeight) {
        double height = Double.parseDouble(strHeight);
        double weight = Double.parseDouble(strWeight);

        model.setHeight(height);
        model.setWeight(weight);

        String msg = "BMI " + decimalFormat.format(model.getBMI()) +
                "; BMI Group: " + model.getBmiGroup();

        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("More...", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showResultsActivity();
                    }
                })
                .show();
    }

    private void showResultsActivity() {
        Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
        intent.putExtra("HEIGHT", model.getHeight());
        intent.putExtra("WEIGHT", model.getWeight());
        intent.putExtra("BMI", decimalFormat.format(model.getBMI()));
        intent.putExtra("BMI_GROUP", model.getBmiGroup());
        startActivity(intent);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
