package com.mj.mkitodl.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mj.mkitodl.utils.Constants;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Frank on 12/31/2015.
 */
public enum Network {
    INSTANCE;

    private final OkHttpClient client;
    private final Retrofit retrofit;

    Network() {
        Gson gson = new GsonBuilder().create();
        client = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
