package com.example.dietapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BmiActivity extends AppCompatActivity {

    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Spinner spinnerGender;
    private Spinner spinnerActivityLevel;
    private Button buttonCalculate, buttonSave;
    private TextView textViewBMIResult;
    private TextView textViewCalorieIntake;
    private SharedPreferences sharedPreferences;

    String url = constants.getUrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        getSupportActionBar().hide(); //hide the title bar


        // Initialize UI components
        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_options, R.layout.spinner_item);
        spinnerGender.setAdapter(adapter);

        spinnerActivityLevel = findViewById(R.id.spinnerActivityLevel);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.activity_level_options, R.layout.spinner_item);
        spinnerActivityLevel.setAdapter(adapter2);

        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonSave = findViewById(R.id.buttonSave);
        textViewBMIResult = findViewById(R.id.textViewBMIResult);
        textViewCalorieIntake = findViewById(R.id.textViewCalorieIntake);

        // Set onClickListener for the Calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
                calculateDailyCalorieIntake();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
            }
        });
    }

    private void saveUserData() {
        String ageStr = editTextAge.getText().toString().trim();
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();

        // Validate input fields
        if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() ||
                spinnerGender.getSelectedItemPosition() == Spinner.INVALID_POSITION ||
                spinnerActivityLevel.getSelectedItemPosition() == Spinner.INVALID_POSITION) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Retrieve user data from UI components
        int age = Integer.parseInt(editTextAge.getText().toString());
        float height = Float.parseFloat(editTextHeight.getText().toString());
        float weight = Float.parseFloat(editTextWeight.getText().toString());
        String gender = spinnerGender.getSelectedItem().toString();
        float activityLevel = getActivityLevelValue();

        // Calculate BMI and calorie intake (assuming methods are implemented)
        float bmi = calculateBMIValue(height, weight);
        double calorieIntake = calculateCalorieIntake(age, gender, weight, height, activityLevel);

        // Retrieve userId from SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");

        // Create UserData object
        UserData userData = new UserData();
        userData.setUserId(userId);
        userData.setAge(age);
        userData.setHeight(height);
        userData.setWeight(weight);
        userData.setGender(gender);
        userData.setActivityLevel(activityLevel);
        userData.setBmi(bmi);
        userData.setCalorieIntake(calorieIntake);

        // Call Retrofit to save user data
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.saveUserData(userData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    Toast.makeText(BmiActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BmiActivity.this, DashboardActivity.class);
//                intent.putExtra("key", "value"); // Add any necessary data
                    startActivity(intent);
                } else {
                    // Handle error response
                    Toast.makeText(BmiActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Toast.makeText(BmiActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calculateBMI() {
        String ageStr = editTextAge.getText().toString().trim();
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();

        // Validate input fields
        if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Retrieve user input values
        int age = Integer.parseInt(editTextAge.getText().toString());
        float height = Float.parseFloat(editTextHeight.getText().toString());
        float weight = Float.parseFloat(editTextWeight.getText().toString());

        // Perform BMI calculation
        float bmi = calculateBMIValue(height, weight);

        // Display BMI result
        textViewBMIResult.setText("Your BMI: " + String.format("%.2f", bmi));
    }

    private float calculateBMIValue(float height, float weight) {
        // Convert height to meters
        float heightInMeters = height / 100; // Assuming height is in centimeters

        // Calculate BMI
        return weight / (heightInMeters * heightInMeters);
    }



    private float getActivityLevelValue() {
        switch (spinnerActivityLevel.getSelectedItemPosition()) {
            case 0: // Sedentary
                return 1.2f;
            case 1: // Lightly active
                return 1.375f;
            case 2: // Moderately active
                return 1.55f;
            case 3: // Very active
                return 1.725f;
            case 4: // Extra active
                return 1.9f;
            default:
                return 1.0f; // Default to sedentary if no selection is made
        }
    }


    private void calculateDailyCalorieIntake() {

        String ageStr = editTextAge.getText().toString().trim();
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();

        // Validate input fields
        if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Retrieve user input values
        int age = Integer.parseInt(editTextAge.getText().toString());
        float height = Float.parseFloat(editTextHeight.getText().toString());
        float weight = Float.parseFloat(editTextWeight.getText().toString());
        String gender = spinnerGender.getSelectedItem().toString();
        float activityLevel = getActivityLevelValue(); // Assuming you have a method to get the activity level value

        // Calculate daily calorie intake
        double calorieIntake = calculateCalorieIntake(age, gender, weight, height, activityLevel);

        // Display calorie intake result
        textViewCalorieIntake.setText("Your Daily Calorie Intake: " + String.format("%.1f", calorieIntake) + " calories");
    }

    private double calculateCalorieIntake(int age, String gender, float weight, float height, float activityLevel) {
        double bmr;
        if (gender.equals("Male")) {
            // Calculate BMR using Mifflin-St Jeor equation for males
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            // Calculate BMR using Mifflin-St Jeor equation for females
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }

        // Adjust BMR based on activity level
        double calorieIntake = bmr * activityLevel;

        return calorieIntake;
    }

}