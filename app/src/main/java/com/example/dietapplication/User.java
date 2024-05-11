package com.example.dietapplication;

public class User {
    private String name;
    private String username;
    private String password;
    private int age;
    private float height;
    private float weight;
    private String gender;
    private float activityLevel;
    private float intakeCalories;

    // Constructor
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.activityLevel = activityLevel;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, float intakeCalories) {
        this.name = name;
        this.intakeCalories = intakeCalories;
    }

    // Getter methods
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public float getActivityLevel() {
        return activityLevel;
    }

    public float getIntakeCalories() {
        return intakeCalories;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setActivityLevel(float activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setIntakeCalories(float intakeCalories){
        this.intakeCalories = intakeCalories;
    }
}
