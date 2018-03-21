package com.project.lostfound;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.project.lostfound.database.LostFoundDatabase;
import com.project.lostfound.helpers.Validation;
import com.project.lostfound.model.User;

import static com.project.lostfound.helpers.Validation.isEmailAddress;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = Register.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextLastName;
    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private LostFoundDatabase lostFoundDatabase;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

        textInputLayoutFirstName = findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = findViewById(R.id.textInputLayoutLastName);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextFirstName = findViewById(R.id.textInputEditTextFirstName);
        textInputEditTextLastName = findViewById(R.id.textInputEditTextLastName);
        textInputEditTextUsername = findViewById(R.id.textInputEditTextUsername);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        lostFoundDatabase = new LostFoundDatabase(activity);
        user = new User();

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    public void verifyOnTextChanged() {

        textInputEditTextFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                Validation.hasText(textInputEditTextFirstName, textInputLayoutFirstName);
            }
        });

        textInputEditTextLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                Validation.hasText(textInputEditTextLastName, textInputLayoutLastName);
            }
        });

        textInputEditTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                Validation.hasText(textInputEditTextUsername, textInputLayoutUsername);
            }
        });

        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                isEmailAddress(textInputEditTextEmail, textInputLayoutEmail, true);
            }
        });

        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                Validation.isPassword(textInputEditTextPassword, textInputLayoutPassword);
            }
        });

        textInputEditTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                Validation.isMatchOk(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputLayoutConfirmPassword);
            }
        });
    }

    public boolean checkValidation() {
        boolean ret = true;

        if ( !Validation.hasText(textInputEditTextFirstName, textInputLayoutFirstName) ) {
            ret = false;
        }

        if ( !Validation.hasText(textInputEditTextLastName, textInputLayoutLastName) ) {
            ret = false;
        }

        if ( !Validation.hasText(textInputEditTextUsername, textInputLayoutUsername)) {
            ret = false;
        }

        if ( !Validation.isEmailAddress(textInputEditTextEmail, textInputLayoutEmail, true) ) {
            textInputLayoutEmail.setError("Enter Valid Email");
            ret = false;
        }

        if ( !Validation.isPassword(textInputEditTextPassword, textInputLayoutPassword) ) {
            ret = false;
        }

        if ( !Validation.isMatchesPassword(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputLayoutConfirmPassword) ) {
            ret = false;
        }

        return ret;
    }
    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        if (checkValidation() ) {
            registerUser();
        } else {
            Toast.makeText(this,"Form contains errors",Toast.LENGTH_LONG).show();
        }
    }

    public void registerUser() {

        String trimEmail = textInputEditTextEmail.getText().toString().trim();
        if (lostFoundDatabase.checkUser(trimEmail)) {
            errorRegisterSnackBar();
        } else {

            user.setUsername(textInputEditTextUsername.getText().toString().trim());
            user.setFirstname(textInputEditTextFirstName.getText().toString().trim());
            user.setLastname(textInputEditTextLastName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            lostFoundDatabase.addUser(user);

            // Snack Bar to show success message that record saved successfully
            successRegisterSnackBar();
            emptyInputEditText();
        }
    }

    public void successRegisterSnackBar() {
        Snackbar snackbar = Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG);
        snackbar.show();

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
    }

    public void errorRegisterSnackBar() {
        Snackbar snackbar = Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG);
        snackbar.show();

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextFirstName.setText(null);
        textInputEditTextLastName.setText(null);
        textInputEditTextUsername.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputLayoutFirstName.setErrorEnabled(false);
        textInputLayoutLastName.setErrorEnabled(false);
        textInputLayoutUsername.setErrorEnabled(false);
        textInputLayoutEmail.setErrorEnabled(false);
        textInputLayoutPassword.setErrorEnabled(false);
        textInputLayoutConfirmPassword.setErrorEnabled(false);
    }
}