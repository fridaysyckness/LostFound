package com.project.lostfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;

import com.project.lostfound.view.ReportFound;
import com.project.lostfound.view.ReportLost;

public class ReportMain extends AppCompatActivity {

    Button lost;
    Button found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().hide();

        lost = findViewById(R.id.buttonLost);
        lost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ReportMain.this, ReportLost.class);
                startActivity(intent);
            }
        });

        found = findViewById(R.id.buttonFound);
        found.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ReportMain.this, ReportFound.class);
                startActivity(intent);
            }
        });

    }
}