package com.example.dietapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NutritionApiService {
    @GET("v1/nutrition")
    Call<NutritionResponse> getNutrition(@Query("query") String query);
}
