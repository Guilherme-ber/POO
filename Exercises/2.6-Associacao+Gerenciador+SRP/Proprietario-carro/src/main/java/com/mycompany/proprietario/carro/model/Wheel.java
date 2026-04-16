package com.mycompany.proprietario.carro.model;

/**
 *
 * @author guilherme
 */
public class Wheel {
    private double radius;
    private String material;
    private String color;

    // Constructor
    public Wheel() {
    }
    public Wheel(double radius, String material, String color) {
        this.radius = radius;
        this.material = material;
        this.color = color;
    }

    // Copy
    public void copy(Wheel other) {
        this.radius = other.getRadius();
        this.material = other.getMaterial();
        this.color = other.getColor();
    }
    
    // Getters and Setters
    public double getRadius() {
        return radius;
    }
    public String getMaterial() {
        return material;
    }
    public String getColor() {
        return color;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
