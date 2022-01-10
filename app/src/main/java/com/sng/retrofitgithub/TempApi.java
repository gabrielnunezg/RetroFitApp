package com.sng.retrofitgithub;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TempApi {

    @GET("/data/2.5/weather?q=moscow&appid=c9dc22a4728290a9d1bfd172b73d71f9&units=metric")
    Call<Temp> getTemp();


}