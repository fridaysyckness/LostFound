package com.project.lostfound.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.project.lostfound.R;
import com.project.lostfound.database.LostFoundDatabase;
import com.project.lostfound.model.Report;

public class ItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        initializeView();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("");



    }

    public void initializeView() {

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        LostFoundDatabase databaseHandler = new LostFoundDatabase(this);
        Report reportDetails = databaseHandler.getReportDetails(id);


        String repordId, itemName, details, location, type, dateReported, brand, contactInfo, remarks;

        repordId = reportDetails.getReportid();
        itemName = reportDetails.getItemname();
        details = reportDetails.getDetails();
        location = reportDetails.getLocation();
        type = reportDetails.getType();
        dateReported = reportDetails.getDatereported();
        brand = reportDetails.getBrand();
        contactInfo = reportDetails.getContactinformation();
        remarks = reportDetails.getRemarks();

        TextView textViewreportId = findViewById(R.id.id);
        TextView textViewItemName = findViewById(R.id.itemName);
        TextView textViewDetails = findViewById(R.id.details);
        TextView textViewLocation = findViewById(R.id.location);
        TextView textViewType = findViewById(R.id.type);
        TextView textViewDateReported = findViewById(R.id.dateReported);
        TextView textViewBrand = findViewById(R.id.brand);
        TextView textViewContactInfo = findViewById(R.id.contactInfo);
        TextView textViewRemarks = findViewById(R.id.remarks);

        textViewreportId.setText(repordId);
        textViewItemName.setText(itemName);
        textViewDetails.setText(details);
        textViewLocation.setText(location);
        textViewType.setText(type);
        textViewDateReported.setText(dateReported);
        textViewBrand.setText(brand);
        textViewContactInfo.setText(contactInfo);
        textViewRemarks.setText(remarks);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
