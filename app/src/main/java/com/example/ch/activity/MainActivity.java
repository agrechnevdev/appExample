package com.example.ch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.ch.App;
import com.example.ch.challenger.R;
import com.example.ch.interfaces.Restapi;
import com.example.ch.model.Status;
import com.example.ch.utils.CustomTextWatcher;

import java.util.HashSet;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends CustomActivity {

    @Inject
    Retrofit retrofit;
    private Restapi restapi;

    @Bind(R.id.inputlay_login)
    TextInputLayout inputlay_login;

    @Bind(R.id.input_login)
    EditText input_login;

    @Bind(R.id.inputlay_password)
    TextInputLayout inputlay_password;

    @Bind(R.id.input_password)
    EditText input_password;

    @Bind(R.id.btn_login)
    Button btn_login;

    @Bind(R.id.main_activity_layout)
    LinearLayout main_activity_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HashSet<String> preferences = (HashSet<String>) getApplicationContext().getSharedPreferences("User-Cookie", Context.MODE_PRIVATE).getStringSet("Cookies", new HashSet<String>());
        if (preferences.isEmpty()) {
            setContentView(R.layout.main_activity);
            ((App) getApplication()).getNetComponent().inject(this);
            ButterKnife.bind(this);
            restapi = retrofit.create(Restapi.class);

            setCheckOnText();
        } else {
            Intent intent = new Intent(getApplicationContext(), CoreActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setCheckOnText() {
        input_login.addTextChangedListener(new CustomTextWatcher(getApplicationContext(), inputlay_login, CustomTextWatcher.WatcherType.LOGIN));
        input_password.addTextChangedListener(new CustomTextWatcher(getApplicationContext(), inputlay_password, CustomTextWatcher.WatcherType.PASSWORD));
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick(R.id.btn_login)
    public void tryLogin() {

        restapi.login(
                input_login.getText().toString(),
                input_password.getText().toString()
        ).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        Intent intent = new Intent(getApplicationContext(), CoreActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Snackbar.make(main_activity_layout, response.body().getErrors().get(0), Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(main_activity_layout, "Внутренняя ошибка сервера", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Snackbar.make(main_activity_layout, "Нет ответа от сервера", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btn_register)
    public void register() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}
