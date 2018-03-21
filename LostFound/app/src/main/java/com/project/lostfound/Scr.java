package com.project.lostfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.project.lostfound.view.Items;
import com.project.lostfound.view.ReportFound;
import com.project.lostfound.view.ReportLost;

public class Scr extends AppCompatActivity {

    Button report;
    Button find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scr);

        getSupportActionBar().hide();

        report = findViewById(R.id.buttonReport);
        report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Scr.this, ReportMain.class);
                startActivity(intent);
            }
        });

        find = findViewById(R.id.buttonFind);
        find.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Scr.this, Items.class);
                startActivity(intent);
            }
        });

    }
}