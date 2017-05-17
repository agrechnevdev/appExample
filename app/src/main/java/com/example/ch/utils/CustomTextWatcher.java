package com.example.ch.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.ch.challenger.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для проверки изменения текста
 */
public class CustomTextWatcher implements TextWatcher {

    Context context;
    TextInputLayout textInputLayout;
    WatcherType watcherType;

    public CustomTextWatcher(Context context, TextInputLayout textInputLayout, WatcherType watherType) {
        this.context = context;
        this.textInputLayout = textInputLayout;
        this.watcherType = watherType;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String strPattern = "^[a-zA-Z0-9]+$";
        Integer checkLength = 1;
        if(watcherType == WatcherType.LOGIN){
            strPattern = "^[a-zA-Z0-9]+$";
            checkLength = 5;
        } else if(watcherType == WatcherType.PASSWORD){
            strPattern = "^[a-zA-Z0-9]+$";
            checkLength = 6;
        } else if(watcherType == WatcherType.EMAIL){
            strPattern = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
        }
        Pattern mPattern = Pattern.compile(strPattern);
        Matcher matcher = mPattern.matcher(s.toString());
        if (s.toString().length() < checkLength) {
            String regex_error_length = context.getResources().getString(R.string.regex_error_length);
            textInputLayout.setError(regex_error_length.replace("4", checkLength.toString()));
        } else if (!matcher.find()) {
            textInputLayout.setError(context.getResources().getString(R.string.regex_error_valid));
        } else {
            textInputLayout.setError(null);
        }
    }

    public enum WatcherType { LOGIN, PASSWORD, EMAIL, TEXT }
}
