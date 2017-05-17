package com.example.ch.activity;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ch.App;
import com.example.ch.challenger.R;
import com.example.ch.fragment.ChallengesFragment;
import com.example.ch.interfaces.Restapi;
import com.example.ch.model.Status;

import java.util.HashSet;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CoreActivity extends CustomActivity implements NavigationView.OnNavigationItemSelectedListener{


    @Inject
    Retrofit retrofit;
    private Restapi restapi;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    ChallengesFragment challengesFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.core_activity);
        ((App) getApplication()).getNetComponent().inject(this);
        ButterKnife.bind(this);
        restapi = retrofit.create(Restapi.class);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        challengesFragment = new ChallengesFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.fragment, challengesFragment)
                .commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        // выходим на страничку логина и удаляем cookie
        if(id == R.id.log_out){

            restapi.logout().enqueue(new Callback<Status>() {

                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {

                    getApplicationContext().getSharedPreferences("User-Cookie", Context.MODE_PRIVATE).edit().clear().apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {
                    Snackbar.make(drawerLayout, "Нет ответа от сервера", Snackbar.LENGTH_LONG).show();
                }
            });

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
