package com.mycompany.airport.manager.model;

public class FidelitySystem {
    private int accumulatedPoints;
    
    // Constructors
    public FidelitySystem() {
        this.accumulatedPoints = 0;
    }
    public FidelitySystem(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }
    
    // Add points
    public void addPoints() {
        this.accumulatedPoints++;
    }
    
    // Verify/Show points
    public int getPoints() {
        return accumulatedPoints;
    }
    
    // Rescue points
    public boolean redeemPoints(int pointsForRedeem) {
        if (pointsForRedeem <= 0 || pointsForRedeem > this.accumulatedPoints) {
            return false;
        } else {
            this.accumulatedPoints -= pointsForRedeem;
            return true;
        }
    }
}