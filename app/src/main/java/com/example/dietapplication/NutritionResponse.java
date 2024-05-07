package com.example.dietapplication;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NutritionResponse {
    @SerializedName("items")
    private List<NutritionItem> items;

    public List<NutritionItem> getItems() {
        return items;
    }
}

class NutritionItem {
    @SerializedName("name")
    private String name;

    @SerializedName("calories")
    private double calories;

    // Add other fields as needed, for example:
    // @SerializedName("fat")
    // private double fat;
    // @SerializedName("protein")
    // private double protein;
    // @SerializedName("carbohydrates")
    // private double carbohydrates;
    // ...

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    // Add getters for other fields as needed
}
