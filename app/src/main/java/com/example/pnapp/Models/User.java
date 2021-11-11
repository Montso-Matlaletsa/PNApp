package com.example.pnapp.Models;

import java.util.List;

public class User {

    private String name;
    private String LastName;
    private String gender;
    private int height;
    private List<Weight> weight;
    private String goal;

    public User(String name, String lastName, String gender, int height, List<Weight> weight, String goal) {
        this.name = name;
        LastName = lastName;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public List<Weight> getWeight() {
        return weight;
    }

    public String getGoal() {
        return goal;
    }
}
