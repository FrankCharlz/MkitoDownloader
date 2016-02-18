package com.mj.mkitodl.network;


import com.mj.mkitodl.models.HomeResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;

public interface MkitoService {

    @GET("/home")
    Call<HomeResponse> getHome(@Header("Cache-Control") String cacheControl);


	

}
