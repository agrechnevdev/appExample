package com.example.ch.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    private Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

//        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("Cookie", Context.MODE_PRIVATE);
//        String cookie = sharedPreferences.getString("Cookie", "");
//        builder.addHeader("Cookie", cookie);

        HashSet<String> preferences = (HashSet<String>) context.getApplicationContext().getSharedPreferences("User-Cookie", Context.MODE_PRIVATE).getStringSet("Cookies", new HashSet<String>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}