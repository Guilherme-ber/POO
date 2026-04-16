package com.mycompany.proprietario.carro.model;

// Managers
import com.mycompany.proprietario.carro.manager.WheelManager;

/**
 *
 * @author guilherme
 */
public class Car {
    private String manufacturer;
    private String model;
    private String color;
    private WheelManager wheelManager;
    
    // Constructor
    public Car() {
        this.wheelManager = new WheelManager();
    }
    public Car(String manufacturer, String model, String color) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.wheelManager = new WheelManager();
    }
    
    // Copy
    public void copy(Car other) {
        this.manufacturer = other.getManufacturer();
        this.model = other.getModel();
        this.color = other.getColor();
    }
   
    // Add Wheel
    public void addWheel(Wheel w) {
        wheelManager.addWheel(w);
    }
    
    // Remove Wheel
    public void removeWheel(int i) {
        wheelManager.removeWheel(i);
    }
    
    // Print
    @Override
    public String toString() {
        return "Carro: " + manufacturer + " " + model + " (" + color + ")";
    }
    
    // Getters and Setters
    public String getManufacturer() {
        return manufacturer;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
