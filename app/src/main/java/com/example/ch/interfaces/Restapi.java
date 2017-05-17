package com.example.ch.interfaces;

import com.example.ch.model.ListChallenges;
import com.example.ch.model.ResponseChallenge;
import com.example.ch.model.Status;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface Restapi {

    @GET("/challenges")
    Call<ResponseChallenge> getChallenge();

    @GET("/challenges")
    Call<ResponseChallenge> getChallengeById(@Query("id") Integer id);

    @GET("/user_challenges")
    Call<ListChallenges> getChallenges();

    @GET("/user_challenges")
    Call<ListChallenges> getChallengesByUserCookie(@Header("Cookie") String cookie);

    @GET("/auth/base/register")
    Call<Status> registerNewUser(@Query("username") String username, @Query("password") String password,
                                 @Query("email") String email, @Query("first_name") String first_name,
                                 @Query("last_name") String last_name);

    @GET("/auth/base/login")
    Call<Status> login(@Query("username_email") String username_email, @Query("password") String password);

    @GET("/auth/base/logout")
    Call<Status> logout();


}