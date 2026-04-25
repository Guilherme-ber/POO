package com.mycompany.airport.manager.model;

// Utils
import java.util.Scanner;

public class Passenger {
    private String name;
    private String cpf;
    private FidelitySystem fidelitySystem;
    
    // Constructors and Copy method
    public Passenger() {
        this.fidelitySystem = new FidelitySystem();
    }
    public Passenger(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.fidelitySystem = new FidelitySystem();
    }
    public void copy(Passenger other) {
        this.name = other.getName();
        this.cpf = other.getCpf();
        this.fidelitySystem = other.getFidelitySystem();
    }
    
    // Fill passenger
    public void fill() {
        Scanner read = new Scanner(System.in);
        
        System.out.println("-- Preencher o usuário --");
        System.out.println("Nome do passageiro: ");
        this.name = read.nextLine();
        System.out.println("CPF do passageiro: ");
        this.cpf = read.nextLine();
    }
    
    // Show passenger
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Nome: ").append(name).append("\n");
        sb.append("CPF: ").append(cpf).append("\n");
        
        return sb.toString();
    }
    
    // Add points
    public void addPoints() {
        fidelitySystem.addPoints();
        System.out.println("1 ponto adicionado ao passageiro " + this.name);
    }
    
    // Show points
    public int showPoints() {
        return fidelitySystem.getPoints();
    }
    
    // Reddem points
    public boolean redeemPoints(int quant) {
        return fidelitySystem.redeemPoints(quant);
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public String getCpf() {
        return cpf;
    }
    public FidelitySystem getFidelitySystem() {
        return fidelitySystem;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}