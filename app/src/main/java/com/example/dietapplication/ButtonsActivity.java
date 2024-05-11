package com.example.dietapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ButtonsActivity extends AppCompatActivity {
    private TextView breakfastBtn, lunchBtn, dinnerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        getSupportActionBar().hide(); //hide the title bar

        breakfastBtn = findViewById(R.id.breakfastBtn);
        lunchBtn = findViewById(R.id.lunchBtn);
        dinnerBtn = findViewById(R.id.dinnerBtn);

        breakfastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonsActivity.this, RecipeActivity.class);
//                intent.putExtra("key", "value"); // Add any necessary data
                startActivity(intent);
            }
        });

        lunchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonsActivity.this, LunchRecipeActivity.class);
//                intent.putExtra("key", "value"); // Add any necessary data
                startActivity(intent);
            }
        });

        dinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonsActivity.this, DinnerRecipeActivity.class);
//                intent.putExtra("key", "value"); // Add any necessary data
                startActivity(intent);
            }
        });
    }
}