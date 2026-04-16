package com.mycompany.proprietario.carro.model;

// Manager
import com.mycompany.proprietario.carro.manager.CarManager;

/**
 *
 * @author guilherme
 */
public class Owner {
    private String name;
    private String cpf;
    private String email;
    private CarManager carManager;
    
    // Constructor
    public Owner() {
        this.carManager = new CarManager();
    }
    public Owner(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.carManager = new CarManager();
    }
    
    // Add Car
    public void addCar(Car car) {
        this.carManager.addCar(car);
    }
    
    // Remove Car
    public void removeCar(int i) {
        carManager.removeCar(i);
    }
    
    // Copy
    public void copy(Owner other) {
        this.name = other.getName();
        this.cpf = other.getCpf();
        this.email = other.getEmail();
    }
    
    // Print
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Proprietário: ").append(name).append("\n");
        sb.append(this.carManager.toString());
        return sb.toString();
    }   
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
