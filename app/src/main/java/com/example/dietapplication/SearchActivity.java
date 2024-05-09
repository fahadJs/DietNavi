package com.example.dietapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter nutritionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dishName = searchEditText.getText().toString().trim();
                if (!dishName.isEmpty()) {
                    // Define your API key
                    String apiKey = "8FKpbZXVZuvBJxxO8I+HLA==KljhFtOZ2g6V833u";

                    // Create OkHttpClient with API key header
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(chain -> {
                                Request request = chain.request().newBuilder()
                                        .addHeader("X-Api-Key", apiKey)
                                        .build();
                                return chain.proceed(request);
                            })
                            .build();

                    // Create a Retrofit instance with the custom OkHttpClient
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.calorieninjas.com/")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    // Create NutritionApiService
                    NutritionApiService nutritionApiService = retrofit.create(NutritionApiService.class);

                    // Make API request
                    Call<NutritionResponse> call = nutritionApiService.getNutrition(dishName);
                    call.enqueue(new Callback<NutritionResponse>() {
                        @Override
                        public void onResponse(Call<NutritionResponse> call, Response<NutritionResponse> response) {
                            // Handle response
                            if (response.isSuccessful()) {
                                NutritionResponse nutritionResponse = response.body();
                                if (nutritionResponse != null && nutritionResponse.getItems() != null) {
                                    // Construct the message to display in the toast
                                    StringBuilder message = new StringBuilder("Nutrition information:\n");
                                    for (NutritionItem item : nutritionResponse.getItems()) {
                                        message.append(item.getName()).append(": ").append(item.getCalories()).append(" calories\n");
                                    }
                                    // Show a toast with the retrieved nutrition information
                                    Toast.makeText(SearchActivity.this, message.toString(), Toast.LENGTH_LONG).show();

                                    nutritionAdapter = new NutritionAdapter(nutritionResponse.getItems()); // Replace MyAdapter with your adapter class name
                                    recyclerView.setAdapter(nutritionAdapter);
                                } else {
                                    // If no nutrition information was found, display a message
                                    Toast.makeText(SearchActivity.this, "No nutrition information found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // If the response was not successful, display an error message
                                Toast.makeText(SearchActivity.this, "Failed to retrieve nutrition information", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<NutritionResponse> call, Throwable t) {
                            // Handle API call failure
                            // Show error message or retry the request
                            Toast.makeText(SearchActivity.this, "Failed to retrieve nutrition information", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(SearchActivity.this, "Please enter a dish name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}