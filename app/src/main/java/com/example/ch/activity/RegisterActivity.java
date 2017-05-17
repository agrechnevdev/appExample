package com.example.ch.activity;


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

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends CustomActivity {

    @Inject
    Retrofit retrofit;
    private Restapi restapi;

    @Bind(R.id.input_username)
    EditText input_username;

    @Bind(R.id.inputlay_username)
    TextInputLayout inputlay_username;

    @Bind(R.id.input_password)
    EditText input_password;

    @Bind(R.id.inputlay_password)
    TextInputLayout inputlay_password;

    @Bind(R.id.input_email)
    EditText input_email;

    @Bind(R.id.inputlay_email)
    TextInputLayout inputlay_email;

    @Bind(R.id.btn_register)
    Button register;

    @Bind(R.id.input_first_name)
    EditText input_first_name;

    @Bind(R.id.inputlay_first_name)
    TextInputLayout inputlay_first_name;

    @Bind(R.id.input_last_name)
    EditText input_last_name;

    @Bind(R.id.inputlay_last_name)
    TextInputLayout inputlay_last_name;

    @Bind(R.id.register_activity_layout)
    LinearLayout register_activity_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ((App) getApplication()).getNetComponent().inject(this);
        ButterKnife.bind(this);
        restapi = retrofit.create(Restapi.class);

        input_username.addTextChangedListener(new CustomTextWatcher(getApplicationContext(), inputlay_username, CustomTextWatcher.WatcherType.LOGIN));
        input_password.addTextChangedListener(new CustomTextWatcher(getApplicationContext(), inputlay_password, CustomTextWatcher.WatcherType.PASSWORD));
    }

    @OnClick(R.id.btn_register)
    public void registerNewUser() {
        restapi.registerNewUser(
                input_username.getText().toString(),
                input_password.getText().toString(),
                input_email.getText().toString(),
                input_first_name.getText().toString(),
                input_last_name.getText().toString()
                ).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                if(response.code() == 200) {
                    if(response.body().getStatus().equals("ok")) {
                        Intent intent = new Intent(getApplicationContext(), CoreActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Snackbar.make(register_activity_layout, response.body().getErrors().get(0), Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(register_activity_layout, "Внутренняя ошибка сервера", Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Snackbar.make(register_activity_layout, "Нет ответа от сервера", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
