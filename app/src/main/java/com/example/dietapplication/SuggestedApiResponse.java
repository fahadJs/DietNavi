package com.example.dietapplication;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SuggestedApiResponse {
    @SerializedName("hits")
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public static class Recipe {
        private String image;
        private String label;
        private double calories;
        private int servingSize;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public double getCalories() {
            return calories;
        }

        public void setCalories(double calories) {
            this.calories = calories;
        }

        public int getServingSize() {
            return servingSize;
        }

        public void setServingSize(int servingSize) {
            this.servingSize = servingSize;
        }
    }
}
