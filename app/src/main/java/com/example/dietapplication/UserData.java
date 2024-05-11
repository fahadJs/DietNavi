package com.example.dietapplication;

public class UserData {
    private String id; // Assuming userId is a String
    private int age;
    private float height;
    private float weight;
    private String gender;
    private float fitness_level;
    private float bmi;
    private double intakeCalories;

    // Constructor
    public UserData() {
    }

    // Getters and setters
    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getActivityLevel() {
        return fitness_level;
    }

    public void setActivityLevel(float fitness_level) {
        this.fitness_level = fitness_level;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public double getCalorieIntake() {
        return intakeCalories;
    }

    public void setCalorieIntake(double intakeCalories) {
        this.intakeCalories = intakeCalories;
    }
}

