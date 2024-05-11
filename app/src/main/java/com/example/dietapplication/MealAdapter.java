package com.example.dietapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private List<Meal> meals;
    private Context context;

    public MealAdapter(List<Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_layout, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView servingTextView;
        private TextView caloriesTextView;
        private TextView proteinTextView;
        private TextView carbsTextView;
        private TextView sugarTextView;
        private TextView fiberTextView;
        private TextView fatTextView;
        private TextView sodiumTextView;
        private TextView cholesterolTextView;
        private TextView totalCal;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            totalCal = itemView.findViewById(R.id.totalCal);
            servingTextView = itemView.findViewById(R.id.textViewServingValue);
            caloriesTextView = itemView.findViewById(R.id.textViewCaloriesValue);
            proteinTextView = itemView.findViewById(R.id.textViewProteinValue);
            carbsTextView = itemView.findViewById(R.id.textViewCarbValue);
            sugarTextView = itemView.findViewById(R.id.textViewSugarValue);
            fiberTextView = itemView.findViewById(R.id.textViewFiberValue);
            fatTextView = itemView.findViewById(R.id.textViewFatValue);
            sodiumTextView = itemView.findViewById(R.id.textViewSodiumValue);
            cholesterolTextView = itemView.findViewById(R.id.textViewCholestrolValue);
        }

        public void bind(Meal meal) {
            nameTextView.setText(meal.getNameItem());
            totalCal.setText(String.format("%.1f", meal.getCalories()));
            servingTextView.setText(String.valueOf(meal.getServing()));
            caloriesTextView.setText(String.format("%.1f", meal.getCalories()));
            proteinTextView.setText(String.format("%.1f", meal.getProtein()));
            carbsTextView.setText(String.format("%.1f", meal.getCarbs()));
            sugarTextView.setText(String.format("%.1f", meal.getSugar()));
            fiberTextView.setText(String.format("%.1f", meal.getFiber()));
            fatTextView.setText(String.format("%.1f", meal.getFat()));
            sodiumTextView.setText(String.format("%.1f", meal.getSodium()));
            cholesterolTextView.setText(String.format("%.1f", meal.getCholesterol()));
        }
    }
}

