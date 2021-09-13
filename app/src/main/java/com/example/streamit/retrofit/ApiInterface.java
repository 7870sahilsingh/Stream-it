package com.example.streamit.retrofit;

import com.example.streamit.model.BannerMovies;

import java.util.List;
import java.util.Observable;

import retrofit2.http.GET;

public interface ApiInterface {

    @GET("banner_movie.json")
    default Observable<List<BannerMovies>> getAllBanners();
}
