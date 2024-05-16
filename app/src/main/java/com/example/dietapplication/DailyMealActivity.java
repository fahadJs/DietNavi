package com.example.dietapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class DailyMealActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private RecyclerView mealRecycler;
    private MealAdapter adapter;

//    private LinearLayout btnDiet, btnBmi, btnProfile;

//    private TextView userName, recommendCal, dailyCal, options, dailyRecommend, addMeal;
    public String urlNew = constants.getUrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_meal);
        getSupportActionBar().hide(); //hide the title bar

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");


//        userName = findViewById(R.id.userName);
//        recommendCal = findViewById(R.id.recommendCal);
//        dailyCal = findViewById(R.id.dailyCal);
//        addMeal = findViewById(R.id.addMeal);
//        btnDiet = findViewById(R.id.btn_suggest);
//        btnBmi = findViewById(R.id.btn_bmi);
//        btnProfile = findViewById(R.id.btn_profile);
//
//        btnDiet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DashboardActivity.this, ButtonsActivity.class);
////                intent.putExtra("key", "value"); // Add any necessary data
//                startActivity(intent);
//            }
//        });
//
//        btnBmi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DashboardActivity.this, BmiActivity.class);
////                intent.putExtra("key", "value"); // Add any necessary data
//                startActivity(intent);
//            }
//        });
//
//        addMeal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DashboardActivity.this, SearchActivity.class);
////                intent.putExtra("key", "value"); // Add any necessary data
//                startActivity(intent);
//            }
//        });

        mealRecycler = findViewById(R.id.mealRecycler);
        mealRecycler.setLayoutManager(new LinearLayoutManager(this));

//        options = findViewById(R.id.options);
//        options.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DashboardActivity.this, BmiActivity.class);
////                intent.putExtra("key", "value"); // Add any necessary data
//                startActivity(intent);
//            }
//        });
//
//        dailyRecommend = findViewById(R.id.dailyRecommend);
//        dailyRecommend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DashboardActivity.this, ButtonsActivity.class);
////                intent.putExtra("key", "value"); // Add any necessary data
//                startActivity(intent);
//            }
//        });


        new DailyMealActivity.FetchMealsTask().execute(userId);
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
                URL url = new URL(urlNew +"user/dailyMeal?id=" + userId+"&date="+currentDate);
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


//                    userName.setText("Hello, "+name);
//                    recommendCal.setText(String.valueOf(intakeCalories));

                }
                final Double finalSumCal = sumCal;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String formattedSumCal = String.format("%.1f", finalSumCal);
//                        dailyCal.setText(formattedSumCal);
                    }
                });

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
                adapter = new MealAdapter(meals, DailyMealActivity.this);
                mealRecycler.setAdapter(adapter);

                Meal firstMeal = meals.get(0); // Assuming the first meal contains user info
//                userName.setText("Hello, " + firstMeal.getName());
//                recommendCal.setText(String.valueOf(firstMeal.getIntakeCalories()));
            } else {
                Toast.makeText(DailyMealActivity.this, "No meals found!", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    public void onBackPressed() {
//        // Build an alert dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Are you sure you want to logout?");
//        builder.setCancelable(false);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Finish the activity and navigate back
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Dismiss the dialog
//                dialog.dismiss();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}