package com.example.dietapplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NutritionItem implements Parcelable {
    @SerializedName("name")
    private String name;

    @SerializedName("calories")
    private double calories;

    @SerializedName("serving_size_g")
    private double servingSize;

    @SerializedName("fat_total_g")
    private double fatTotal;

    @SerializedName("fat_saturated_g")
    private double fatSaturated;

    @SerializedName("protein_g")
    private double protein;

    @SerializedName("sodium_mg")
    private double sodium;

    @SerializedName("potassium_mg")
    private double potassium;

    @SerializedName("cholesterol_mg")
    private double cholesterol;

    @SerializedName("carbohydrates_total_g")
    private double carbohydratesTotal;

    @SerializedName("fiber_g")
    private double fiber;

    @SerializedName("sugar_g")
    private double sugar;

    // Constructor
    public NutritionItem(String name, double calories, double servingSize, double fatTotal, double fatSaturated, double protein, double sodium, double potassium, double cholesterol, double carbohydratesTotal, double fiber, double sugar) {
        this.name = name;
        this.calories = calories;
        this.servingSize = servingSize;
        this.fatTotal = fatTotal;
        this.fatSaturated = fatSaturated;
        this.protein = protein;
        this.sodium = sodium;
        this.potassium = potassium;
        this.cholesterol = cholesterol;
        this.carbohydratesTotal = carbohydratesTotal;
        this.fiber = fiber;
        this.sugar = sugar;
    }

    protected NutritionItem(Parcel in) {
        name = in.readString();
        calories = in.readDouble();
        servingSize = in.readDouble();
        fatTotal = in.readDouble();
        fatSaturated = in.readDouble();
        protein = in.readDouble();
        sodium = in.readDouble();
        potassium = in.readDouble();
        cholesterol = in.readDouble();
        carbohydratesTotal = in.readDouble();
        fiber = in.readDouble();
        sugar = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(calories);
        dest.writeDouble(servingSize);
        dest.writeDouble(fatTotal);
        dest.writeDouble(fatSaturated);
        dest.writeDouble(protein);
        dest.writeDouble(sodium);
        dest.writeDouble(potassium);
        dest.writeDouble(cholesterol);
        dest.writeDouble(carbohydratesTotal);
        dest.writeDouble(fiber);
        dest.writeDouble(sugar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NutritionItem> CREATOR = new Creator<NutritionItem>() {
        @Override
        public NutritionItem createFromParcel(Parcel in) {
            return new NutritionItem(in);
        }

        @Override
        public NutritionItem[] newArray(int size) {
            return new NutritionItem[size];
        }
    };

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getServingSize() {
        return servingSize;
    }

    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }

    public double getFatTotal() {
        return fatTotal;
    }

    public void setFatTotal(double fatTotal) {
        this.fatTotal = fatTotal;
    }

    public double getFatSaturated() {
        return fatSaturated;
    }

    public void setFatSaturated(double fatSaturated) {
        this.fatSaturated = fatSaturated;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getCarbohydratesTotal() {
        return carbohydratesTotal;
    }

    public void setCarbohydratesTotal(double carbohydratesTotal) {
        this.carbohydratesTotal = carbohydratesTotal;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "NutritionItem{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", servingSize=" + servingSize +
                ", fatTotal=" + fatTotal +
                ", fatSaturated=" + fatSaturated +
                ", protein=" + protein +
                ", sodium=" + sodium +
                ", potassium=" + potassium +
                ", cholesterol=" + cholesterol +
                ", carbohydratesTotal=" + carbohydratesTotal +
                ", fiber=" + fiber +
                ", sugar=" + sugar +
                '}';
    }
}
