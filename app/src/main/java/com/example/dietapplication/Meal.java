package com.example.dietapplication;

public class Meal {
    private int id;
    private int userId;
    private String date;
    private String name_item;
    private double serving;
    private double calories;
    private double protein;
    private double carbs;
    private double sugar;
    private double fiber;
    private double fat;
    private double sodium;
    private double cholesterol;

    private String name;
    private String username;
    private int age;
    private Float height;
    private Float weight;
    private String gender;
    private String fitness_level;
    private Float bmi;
    private Float intakeCalories;

    // Constructor, getters, and setters
    // Constructor
    public Meal(int id, int userId, String date, String name_item, double serving, double calories, double protein, double carbs, double sugar, double fiber, double fat, double sodium, double cholesterol, String name, String username, int age, Float height, Float weight, String gender, String fitness_level, Float bmi, Float intakeCalories) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.name_item = name_item;
        this.serving = serving;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.sugar = sugar;
        this.fiber = fiber;
        this.fat = fat;
        this.sodium = sodium;
        this.cholesterol = cholesterol;
        this.name = name;
        this.username = username;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.fitness_level = fitness_level;
        this.bmi = bmi;
        this.intakeCalories = intakeCalories;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameItem() {
        return name_item;
    }

    public void setNameItem(String name) {
        this.name_item = name_item;
    }

    public double getServing() {
        return serving;
    }

    public void setServing(double serving) {
        this.serving = serving;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for 'username'
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter methods for 'age'
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter and setter methods for 'height'
    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    // Getter and setter methods for 'weight'
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    // Getter and setter methods for 'gender'
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and setter methods for 'fitness_level'
    public String getFitness_level() {
        return fitness_level;
    }

    public void setFitness_level(String fitness_level) {
        this.fitness_level = fitness_level;
    }

    // Getter and setter methods for 'bmi'
    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    // Getter and setter methods for 'intakeCalories'
    public Float getIntakeCalories() {
        return intakeCalories;
    }

    public void setIntakeCalories(Float intakeCalories) {
        this.intakeCalories = intakeCalories;
    }
}
