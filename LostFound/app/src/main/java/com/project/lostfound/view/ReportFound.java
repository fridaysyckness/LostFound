package com.project.lostfound.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.project.lostfound.R;
import com.project.lostfound.database.LostFoundDatabase;
import com.project.lostfound.helpers.Validation;
import com.project.lostfound.model.Report;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;
import java.util.Date;

public class ReportFound extends AppCompatActivity implements View.OnClickListener {

    String[] Categories = {"Apparel", "Accesories", "Gadgets", "School Supply", "Jewelry", "Money", "Others"};
    final AppCompatActivity activity = ReportFound.this;

    NestedScrollView nestedScrollView;

    TextInputLayout textInputLayoutItemName;
    TextInputLayout textInputLayoutDetails;
    TextInputLayout textInputLayoutLocation;
    TextInputLayout textInputLayoutBrand;
    TextInputLayout textInputLayoutContactInformation;
    TextInputLayout textInputLayoutRemarks;

    private MaterialBetterSpinner materialDesignSpinner;

    TextInputEditText textInputEditTextItemName;
    TextInputEditText textInputEditTextDetails;
    TextInputEditText textInputEditLocation;
    TextInputEditText textInputEditTextBrand;
    TextInputEditText textInputEditContactInformation;
    TextInputEditText textInputEditRemarks;

    AppCompatButton appCompatButtonReport;
    ArrayAdapter<String> arrayAdapter;

    LostFoundDatabase lostFoundDatabase;
    Report report;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
        verifyOnTextChanged();
    }
    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutItemName = findViewById(R.id.textInputLayoutItemName);
        textInputLayoutDetails = findViewById(R.id.textInputLayoutDetails);
        textInputLayoutLocation = findViewById(R.id.textInputLayoutLocation);
        textInputLayoutBrand = findViewById(R.id.textInputLayoutBrand);
        textInputLayoutContactInformation = findViewById(R.id.textInputLayoutContactInformation);
        textInputLayoutRemarks = findViewById(R.id.textInputLayoutRemarks);

        textInputEditTextItemName = findViewById(R.id.textInputEditTextItemName);
        textInputEditTextDetails = findViewById(R.id.textInputEditTextDetails);
        textInputEditLocation = findViewById(R.id.textInputEditLocation);
        textInputEditTextBrand = findViewById(R.id.textInputEditTextBrand);
        textInputEditContactInformation = findViewById(R.id.textInputEditContactInformation);
        textInputEditRemarks = findViewById(R.id.textInputEditRemarks);

        appCompatButtonReport = findViewById(R.id.appCompatButtonReport);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Categories);
        materialDesignSpinner = findViewById(R.id.android_material_design_spinner);

    }

    private void initListeners() {
        appCompatButtonReport.setOnClickListener(this);
    }

    private void initObjects() {
        lostFoundDatabase = new LostFoundDatabase(activity);
        report = new Report();
        materialDesignSpinner.setAdapter(arrayAdapter);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonReport:
                postDataToSQLite();
                break;

        }
    }

    public void verifyOnTextChanged() {

        textInputEditTextItemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                Validation.hasText(textInputEditTextItemName, textInputLayoutItemName);
            }
        });
    }

    public boolean checkValidation() {
        boolean ret = true;

        if ( !Validation.hasText(textInputEditTextItemName, textInputLayoutItemName) ) {
            ret = false;
        }

        return ret;
    }
    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        if (checkValidation() ) {
            reportFound();
        } else {
            Toast.makeText(this,"Form contains errors",Toast.LENGTH_LONG).show();
        }
    }

    public void reportFound() {

        String trimItemName = textInputEditTextItemName.getText().toString().trim();
        if (lostFoundDatabase.checkUser(trimItemName)) {
            errorRegisterSnackBar();
        } else {

            Date currentTime = Calendar.getInstance().getTime();
            String dateToday = String.valueOf(currentTime);
            report.setItemname(textInputEditTextItemName.getText().toString().trim());
            report.setDetails(textInputEditTextDetails.getText().toString().trim());
            report.setLocation(textInputEditLocation.getText().toString().trim());
            report.setType("found");
            report.setDatereported(dateToday);
            report.setBrand(textInputEditTextBrand.getText().toString().trim());
            report.setCategory(materialDesignSpinner.getText().toString().trim());
            report.setContactinformation(textInputEditContactInformation.getText().toString().trim());
            report.setRemarks(textInputEditRemarks.getText().toString().trim());

            lostFoundDatabase.addReport(report);

            // Snack Bar to show success message that record saved successfully
            successRegisterSnackBar();
            emptyInputEditText();
        }
    }

    public void successRegisterSnackBar() {
        Snackbar snackbar = Snackbar.make(nestedScrollView, getString(R.string.success_report), Snackbar.LENGTH_LONG);
        snackbar.show();

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
    }

    public void errorRegisterSnackBar() {
        Snackbar snackbar = Snackbar.make(nestedScrollView, getString(R.string.error_forms), Snackbar.LENGTH_LONG);
        snackbar.show();

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextItemName.setText(null);
        textInputEditTextDetails.setText(null);
        textInputEditLocation.setText(null);
        textInputEditTextBrand.setText(null);
        textInputEditContactInformation.setText(null);
        textInputEditRemarks.setText(null);

        textInputLayoutDetails.setErrorEnabled(false);
        textInputLayoutLocation.setErrorEnabled(false);
        textInputLayoutBrand.setErrorEnabled(false);
        textInputLayoutContactInformation.setErrorEnabled(false);
        textInputLayoutRemarks.setErrorEnabled(false);
    }
}
