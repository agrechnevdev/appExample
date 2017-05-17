package com.example.ch.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ch.App;
import com.example.ch.adapters.RecyclerAdapter;
import com.example.ch.challenger.R;
import com.example.ch.interfaces.Restapi;
import com.example.ch.model.Challenge;
import com.example.ch.model.ListChallenges;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChallengesFragment extends Fragment{

    @Inject
    Retrofit retrofit;

    private Restapi restapi;
    private List<Challenge> challenges;

    @Bind(R.id.list_challenges)
    RecyclerView list_challenges;

    @Bind(R.id.challenges_fragment)
    FrameLayout challenges_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenges_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((App) getActivity().getApplication()).getNetComponent().inject(this);


        restapi = retrofit.create(Restapi.class);


//        list_challenges = (RecyclerView) getView().findViewById(R.id.list_challenges);
        restapi.getChallenges().enqueue(new Callback<ListChallenges>() {
            @Override
            public void onResponse(Call<ListChallenges> call, Response<ListChallenges> response) {

                if(response.code() == 200) {
                    if(response.body().getStatus().equals("ok")) {
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        list_challenges.setLayoutManager(llm);
                        RecyclerAdapter adapter = new RecyclerAdapter(response.body().getChallenges(), getActivity());
                        list_challenges.setAdapter(adapter);
                    } else {
                        Snackbar.make(challenges_fragment, response.body().getErrors().get(0), Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(challenges_fragment, "Внутренняя ошибка сервера", Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ListChallenges> call, Throwable t) {
                Snackbar.make(challenges_fragment, "Нет ответа от сервера", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
