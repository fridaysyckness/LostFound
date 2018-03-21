package com.project.lostfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.lostfound.view.Items;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button report;
    Button find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        button = findViewById(R.id.buttonMain);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Login.class);
                startActivity(myIntent);
            }
        });

/*        report = findViewById(R.id.buttonReport);
        report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, ReportMain.class);
                startActivity(intent);
            }
        });*/

        find = findViewById(R.id.buttonFind);
        find.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, Items.class);
                startActivity(intent);
            }
        });



    }
}