package com.example.bmicalc;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private TextView mheight, mweight, mBMI, mBMIGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setupToolbar();
        setupFAB();
        setupTextViews();
        processIncomingData();
    }

    private void processIncomingData() {
        Bundle extras = getIntent().getExtras();
        double height = extras.getDouble("HEIGHT");
        double weight = extras.getDouble("WEIGHT");
        String BMI = extras.getString("BMI");
        String BMIGroup = extras.getString("BMI_GROUP");

        String strHeight = getString(R.string.height_label_text) + height;
        String strWeight = getString(R.string.weight_label_text) + weight;
        String strBMI = getString(R.string.BMI) + BMI;
        String strBMIGroup = getString(R.string.BMI_Group) + BMIGroup;

        mheight.setText(strHeight);
        mweight.setText(strWeight);
        mBMI.setText(strBMI);
        mBMIGroup.setText(strBMIGroup);
    }

    private void setupTextViews() {
        mheight = findViewById(R.id.results_tv_height);
        mweight = findViewById(R.id.results_tv_weight);
        mBMI = findViewById(R.id.results_tv_BMI);
        mBMIGroup = findViewById(R.id.results_tv_BMI_Group);
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
