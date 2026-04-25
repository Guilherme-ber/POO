package com.mycompany.airport.manager.model;

// Manager
import com.mycompany.airport.manager.manager.*;

// Utils
import java.util.Scanner;

public class Airport {
    private String name;
    private String local;
    private FlightListManager flightListManager;
    
    // Constructors
    public Airport() {
        this.flightListManager = new FlightListManager();
    }
    public Airport(String name, String local) {
        this.name = name;
        this.local = local;
        this.flightListManager = new FlightListManager();
    }
    
    // Fill
    public void fill(Scanner read) {
        System.out.println("-- Preencher informacoes do Aeroporto --");
        System.out.println("Nome do Aeroporto: ");
        this.name = read.nextLine();
        System.out.println("Localizacao do Aeroporto: ");
        this.local = read.nextLine();
    }
    
    // Add flight
    public void addFlight(Flight f) {    
        flightListManager.addFlight(f);
    }
    
    // Remove flight
    public void removeFlight(int flightNumber) {
        flightListManager.removeFlight(flightNumber);
    }
    
    // List flights
    public void listFlights() {
        flightListManager.listFlights();
    }
    
    // Flights with prejudice
    public String getFlightsWithPrejudice() {
        return flightListManager.getFlightsWithPrejudice();
    }
    
    // Start flight
    public void startFlight(int flightNumber) {
        flightListManager.startFlight(flightNumber);
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public String getLocal() {
        return local;
    }
    public FlightListManager getFlightListManager() {
        return flightListManager;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setFlightListManager(FlightListManager flightListManager) {
        this.flightListManager = flightListManager;
    }
}