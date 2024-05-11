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

