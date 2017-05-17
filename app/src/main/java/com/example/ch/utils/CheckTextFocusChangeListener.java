package com.example.ch.utils;


import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.example.ch.challenger.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckTextFocusChangeListener implements View.OnFocusChangeListener {

    private String stringBefore;
    EditText targetEditText;            // изменяемый текст
    Context context;
    TextInputLayout textInputLayout;

    public CheckTextFocusChangeListener(EditText targetEditText, Context context, TextInputLayout textInputLayout) {
        this.targetEditText = targetEditText;
        this.context = context;
        this.textInputLayout = textInputLayout;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            String text = targetEditText.getText().toString();
            Pattern mPattern = Pattern.compile("^[a-zA-Z0-9]+$");
            Matcher matcher = mPattern.matcher(text);
            textInputLayout.setError(null);
            if (text.length() < 6) {
                textInputLayout.setError(context.getResources().getString(R.string.regex_error_length));
            } else if (!matcher.find()) {
                //Toast.makeText(context, "Можно вводить только числа или буквы!", Toast.LENGTH_LONG).show();
                if (textInputLayout != null) {
                    textInputLayout.setError(context.getResources().getString(R.string.regex_error_valid));
                }
            } else {
                textInputLayout.setError(null);
            }
        }
    }
}
