package com.mycompany.airport.manager.model;

// Managers
import com.mycompany.airport.manager.manager.*;

// Utils
import java.util.Scanner;

public class Flight {
    private int flightNumber;
    private String destination;
    private int maximumPassengerCapacity;
    private String flightStatus;
    private PassengerManager passengerManager;
    private StopoverManager stopoverManager;
    
    // Constructors and Copy method
    public Flight() {
        this.flightNumber = 0;
        this.destination = "";
        this.maximumPassengerCapacity = 0;
        this.flightStatus = "Aguardando decolagem";
        this.passengerManager = new PassengerManager();
        this.stopoverManager = new StopoverManager();
        
    }
    public Flight(int flightNumber, String destination, int maximumPassengerCapacity, String flightStatus) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.maximumPassengerCapacity = maximumPassengerCapacity;
        this.flightStatus = flightStatus;
        this.passengerManager = new PassengerManager();
        this.stopoverManager = new StopoverManager();
    }
    public void copy(Flight other){
        this.flightNumber = other.getFlightNumber();
        this.destination = other.getDestination();
        this.maximumPassengerCapacity = other.getMaximumPassengerCapacity();
        this.flightStatus = other.getFlightStatus();
    }
    
    // Fill
    public void fill(Scanner read) {
        System.out.println("-- Preencher informacoes do Voo --");
        System.out.println("Numero do Voo: ");
        this.flightNumber = read.nextInt();
        System.out.println("Destino do Voo: ");
        this.destination = read.nextLine();
        System.out.println("Capacidade maxima de passageiros do Voo: ");
        this.maximumPassengerCapacity = read.nextInt();
        System.out.println("Status do Voo: ");
        this.flightStatus = read.nextLine();
    }
    
    // Add passenger
    public void addPassenger(Passenger p){
        passengerManager.addPassenger(p);
    }
    
    // Remove passenger
    public void removePassenger(){
        Scanner read = new Scanner(System.in);

        System.out.println("Informe o CPF do Passageiro: ");
        String cpf = read.nextLine();
        passengerManager.removePassenger(cpf);
    }
    
    // Add stopover
    public void addStopover(String stopover){
        stopoverManager.addStopover(stopover);
    }
    
    // Remove stopover
    public void removeStopover(){
        Scanner read = new Scanner(System.in);

        System.out.println("Informe a escala que deseja remover: ");
        String local = read.nextLine();
        stopoverManager.removeStopover(local);
    }
    
    // Switch flight state
    public void switchFlightState(){
        Scanner read = new Scanner(System.in);
        System.out.println("Selecione o Estado de voo");
        System.out.println("1 - Aguardando decolagem \n2 - Voando \n3 - Concluido");
        int option = read.nextInt();
        read.nextLine();

        for(int i = 0; i >= 0; i++) {
           switch(option) {
               case 1:
                   setFlightStatus("Aguardando decolagem");
                   break;
               case 2:
                   setFlightStatus("Voando");
                   break;
               case 3:
                   setFlightStatus("Concluido");
                   break;
           }
        }  
    }
    
    // Check minimal capacity (5 passengers)
    public boolean checkCapacity(){
        return passengerManager.checkCapacity();
    }
    
    // To String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== Dados do Voo ===\n");
        sb.append("Número do voo: ").append(flightNumber).append("\n");
        sb.append("Destino: ").append(destination).append("\n");
        sb.append("Capacidade máxima: ").append(maximumPassengerCapacity).append("\n");
        sb.append("Estado do voo: ").append(flightStatus).append("\n");
        
        if (passengerManager.getPassengerList().isEmpty()) sb.append("Nenhum passageiro cadastrado.\n");
        else passengerManager.toString(sb);
        
        if (stopoverManager.getStopoverList().isEmpty()) sb.append("Nenhuma escala cadastrada.\n");
        else stopoverManager.toString(sb);

        sb.append("\nTotal de passageiros: ").append(passengerManager.getPassengerList().size());

        return sb.toString();
    }

    // Getters and Setters
    public int getFlightNumber() {
        return flightNumber;
    }
    public String getDestination() {
        return destination;
    }
    public int getMaximumPassengerCapacity() {
        return maximumPassengerCapacity;
    }
    public String getFlightStatus() {
        return flightStatus;
    }
    public PassengerManager getPassengerManager() {
        return passengerManager;
    }
    
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setMaximumPassengerCapacity(int maximumPassengerCapacity) {
        this.maximumPassengerCapacity = maximumPassengerCapacity;
    }
    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }
    public void setPassengerManager(PassengerManager passengerManager) {
        this.passengerManager = passengerManager;
    }
}