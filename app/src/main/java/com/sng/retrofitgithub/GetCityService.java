package com.sng.retrofitgithub;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCityService {

    @GET("/photos")
    Call<List<RetroCity>> getAllPhotos();
}