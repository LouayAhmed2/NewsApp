package com.route.apis;

import com.route.apis.model.NewsResponse;
import com.route.apis.model.SourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface WebServices {

    @GET("sources")
    Call<SourcesResponse> getNewsSources(@Query("apiKey") String apikey);

    @GET("everything")
    Call<NewsResponse> getNewsBySourceId(@Query("apiKey") String apikey,
                                         @Query("sources") String sourceId
                                         ,@Header("auth") String token
    );

    Call<NewsResponse> getNewsBySourceId(String apiKey, String sourceId);
}
