package com.example.dietapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private RecyclerView dailyMealRecycler;
    private MealAdapter adapter;

    private TextView userName, recommendCal, dailyCal, options, dailyRecommend, addMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide(); //hide the title bar

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");


        userName = findViewById(R.id.userName);
        recommendCal = findViewById(R.id.recommendCal);
        dailyCal = findViewById(R.id.dailyCal);
        addMeal = findViewById(R.id.addMeal);
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SearchActivity.class);
//                intent.putExtra("key", "value"); // Add any necessary data
                startActivity(intent);
            }
        });

        dailyMealRecycler = findViewById(R.id.dailyMealRecycler);
        dailyMealRecycler.setLayoutManager(new LinearLayoutManager(this));

        options = findViewById(R.id.options);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, BmiActivity.class);
//                intent.putExtra("key", "value"); // Add any necessary data
                startActivity(intent);
            }
        });

        dailyRecommend = findViewById(R.id.dailyRecommend);
        dailyRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ButtonsActivity.class);
//                intent.putExtra("key", "value"); // Add any necessary data
                startActivity(intent);
            }
        });


        new FetchMealsTask().execute(userId);
    }

    private class FetchMealsTask extends AsyncTask<String, Void, List<Meal>> {

        @Override
        protected List<Meal> doInBackground(String... userIds) {
            String userId = userIds[0];
            List<Meal> meals = new ArrayList<>();
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String currentDate = Utils.getCurrentDate();

            try {
                URL url = new URL("http://10.0.2.2:3030/user/dailyMeal?id=" + userId+"&date="+currentDate);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                Double sumCal = 0.0;
                String json = buffer.toString();
//                List<Meal> meals = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    // Parse JSON data and create Meal objects
                    int id = jsonObject.getInt("id");
                    int userIdN = jsonObject.getInt("user_id");
                    String date = jsonObject.getString("date");
                    String name_item = jsonObject.getString("name_item");
                    double serving = jsonObject.getDouble("serving");
                    double calories = jsonObject.getDouble("calories");
                    double protein = jsonObject.getDouble("protein");
                    double carbs = jsonObject.getDouble("carbs");
                    double sugar = jsonObject.getDouble("sugar");
                    double fiber = jsonObject.getDouble("fiber");
                    double fat = jsonObject.getDouble("fat");
                    double sodium = jsonObject.getDouble("sodium");
                    double cholesterol = jsonObject.getDouble("cholesterol");
                    String name = jsonObject.getString("name");
                    String username = jsonObject.getString("username");
                    int age = jsonObject.getInt("age");
                    Float height = (float) jsonObject.getDouble("height");
                    Float weight = (float) jsonObject.getDouble("weight");
                    String gender = jsonObject.getString("gender");
                    String fitness_level = jsonObject.getString("fitness_level");
                    Float bmi = (float) jsonObject.getDouble("bmi");
                    Float intakeCalories = (float) jsonObject.getDouble("intakeCalories");
                    // Create a Meal object and add it to the list
                    Meal meal = new Meal(id, userIdN, date, name_item, serving, calories, protein, carbs, sugar, fiber, fat, sodium, cholesterol, name, username, age, height, weight, gender, fitness_level, bmi, intakeCalories);
                    meals.add(meal);

                    sumCal += calories;


                    userName.setText("Hello, "+name);
                    recommendCal.setText(String.valueOf(intakeCalories));

                }
                String formattedSumCal = String.format("%.1f", sumCal);
                dailyCal.setText(formattedSumCal);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return meals;
        }

        @Override
        protected void onPostExecute(List<Meal> meals) {
            super.onPostExecute(meals);
            if (!meals.isEmpty()) {
                adapter = new MealAdapter(meals, DashboardActivity.this);
                dailyMealRecycler.setAdapter(adapter);
            } else {
                Toast.makeText(DashboardActivity.this, "No meals found!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
