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
    public void addFlight() {
        Scanner read = new Scanner(System.in);
        
        Flight f = new Flight();
        f.fill(read);
        
        flightListManager.addFlight(f);
    }
    
    // Remove flight
    public void removeFlight() {
        Scanner read = new Scanner(System.in);
        int flightNumber;
        
        System.out.println("Informe o número do voo para remocao: ");
        flightNumber = read.nextInt();
        read.nextLine();
        
        flightListManager.removeFlight(flightNumber);
    }
    
    // List flights
    public void listFlights() {
        flightListManager.listFlights();
    }
    
    // Flights with prejudice
    public void getFlightsWithPrejudice() {
        flightListManager.getFlightsWithPrejudice();
    }
    
    // Start flight
    public void startFlight() {
        Scanner read = new Scanner(System.in);
        int flightNumber;
        
        System.out.println("Informe o número do voo para iniciar a decolagem: ");
        flightNumber = read.nextInt();
        read.nextLine();
        
        System.out.println("Iniciando voo...");
        flightListManager.startFlight(flightNumber);
        }
    
    // End flight
    public void endFlight() {
        Scanner read = new Scanner(System.in);
        int flightNumber;
        
        System.out.println("Informe o número do voo para finalizar a viagem: ");
        flightNumber = read.nextInt();
        read.nextLine();
        
        System.out.println("Finalizando voo...");
        flightListManager.endFlight(flightNumber);
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