package com.project.lostfound.helpers;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Validation {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";

    // Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "Enter Valid Email";
    private static final String PHONE_MSG = "###-#######";
    private static final String PASSWORD_MSG = "Enter Password";
    private static final String REQUIRED_FIELDS = "Required Fields";
    private static final String PASSWORD_NOT_MATCH = "Password does not match";

    // call this method when you need to check email validation
/*    public static boolean isEmailAddress(TextInputEditText textInputEditText, boolean required) {
        return isValid(textInputEditText, EMAIL_REGEX, EMAIL_MSG, required);
    }*/

    public static boolean isEmailAddress(TextInputEditText textInputEditText, TextInputLayout textInputLayout, boolean required) {
        return isValid(textInputEditText, textInputLayout, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(TextInputEditText textInputEditText, TextInputLayout textInputLayout, boolean required) {
        return isValid(textInputEditText, textInputLayout, PHONE_REGEX, PHONE_MSG, required);
    }

    public static boolean isPassword(TextInputEditText textInputEditText, TextInputLayout textInputLayout) {
        return isFieldEmpty(textInputEditText, textInputLayout, PASSWORD_MSG);
    }

    public static boolean isMatchesPassword(TextInputEditText textInputEditText, TextInputEditText textInputEditText2, TextInputLayout textInputLayout) {
        return isMatchOk(textInputEditText, textInputEditText2, textInputLayout);
    }

    public static boolean isRequired(TextInputEditText textInputEditText, TextInputLayout textInputLayout) {
        return isFieldEmpty(textInputEditText, textInputLayout, REQUIRED_FIELDS);
    }


    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String regex, String errMsg, boolean required) {

        String text = textInputEditText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        textInputEditText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(textInputEditText, textInputLayout) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(errMsg);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(TextInputEditText textInputEditText, TextInputLayout textInputLayout) {

        String text = textInputEditText.getText().toString().trim();
        textInputEditText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(REQUIRED_FIELDS);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    public static boolean isFieldEmpty(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String errMsg) {

        String text = textInputEditText.getText().toString().trim();
        textInputEditText.setError(null);

        if(text.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(errMsg);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public static boolean isMatchOk(TextInputEditText password, TextInputEditText confirmPassword, TextInputLayout textInputLayout) {

        String text = password.getText().toString().trim();
        String text2 = confirmPassword.getText().toString().trim();
        textInputLayout.setError(null);

        if(!text.contentEquals(text2)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(PASSWORD_NOT_MATCH);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
}