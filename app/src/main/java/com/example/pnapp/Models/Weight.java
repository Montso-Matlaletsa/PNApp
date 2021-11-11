package com.example.pnapp.Models;

public class Weight {

    private String id;
    private String date;
    private Float weight;
    private Double start;
    private Double goal;

    public Weight(String id, String date, Float weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public Weight(String id, String date, Float weight, Double start, Double goal) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.start = start;
        this.goal = goal;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Float getWeight() {
        return weight;
    }

    public Double getStart() {
        return start;
    }

    public Double getGoal() {
        return goal;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public void setGoal(Double goal) {
        this.goal = goal;
    }
}
