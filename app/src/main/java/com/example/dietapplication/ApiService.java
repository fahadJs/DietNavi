package com.example.dietapplication;
import com.google.gson.annotations.SerializedName;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/user/register")
    Call<Void> registerUser(@Body User user);

    @POST("/user/login")
    Call<LoginResponse> loginUser(@Body User user);

    @POST("/user/saveItem") // Replace "saveItem" with the actual endpoint
    Call<ResponseBody> saveItem(@Body RequestBody requestBody);

    @POST("/user/updateInfo")
    Call<Void> saveUserData(@Body UserData userData);
}

