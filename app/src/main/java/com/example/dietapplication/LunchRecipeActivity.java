package com.example.dietapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LunchRecipeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    public String urlNew = constants.getUrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        getSupportActionBar().hide(); //hide the title bar

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(this);
        recyclerView.setAdapter(adapter);

        fetchRecipes();
    }

    private void fetchRecipes() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlNew+"user/suggest/lunch")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                final String responseData = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<List<SuggestedApiResponse.Recipe>>(){}.getType();
                final List<SuggestedApiResponse.Recipe> recipes = gson.fromJson(responseData, type);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setRecipes(recipes);
                    }
                });
            }
        });
    }
}
