package com.example.demo.model;

public class VoteAverage {
    private double averageScore;
    private int count;

    public VoteAverage(double averageScore, int count) {
        this.averageScore = averageScore;
        this.count = count;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
