package com.travelease.nitant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("TravelEase/registration.php")
    Call<ResultUInfo> insert(@Field("uname") String name,
                             @Field("contact") Double contact,
                             @Field("email") String email,
                             @Field("password") String password);


    @FormUrlEncoded
    @POST("TravelEase/city.php")
    Call<ResultCity> getCityLocation(@Field("city") String city);

    @GET("TravelEase/cityname.php")
    Call<ResultCName> getCityName();

}
