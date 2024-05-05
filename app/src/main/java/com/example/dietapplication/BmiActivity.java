package com.example.dietapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Spinner spinnerGender;
    private Spinner spinnerActivityLevel;
    private Button buttonCalculate;
    private TextView textViewBMIResult;
    private TextView textViewCalorieIntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        // Initialize UI components
        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerActivityLevel = findViewById(R.id.spinnerActivityLevel);
        buttonCalculate = findViewById(R.id.buttonCalculate);
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
    }

    private void calculateBMI() {
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
        // Retrieve user input values
        int age = Integer.parseInt(editTextAge.getText().toString());
        float height = Float.parseFloat(editTextHeight.getText().toString());
        float weight = Float.parseFloat(editTextWeight.getText().toString());
        String gender = spinnerGender.getSelectedItem().toString();
        float activityLevel = getActivityLevelValue(); // Assuming you have a method to get the activity level value

        // Calculate daily calorie intake
        double calorieIntake = calculateCalorieIntake(age, gender, weight, height, activityLevel);

        // Display calorie intake result
        textViewCalorieIntake.setText("Your Daily Calorie Intake: " + String.format("%.2f", calorieIntake) + " calories");
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