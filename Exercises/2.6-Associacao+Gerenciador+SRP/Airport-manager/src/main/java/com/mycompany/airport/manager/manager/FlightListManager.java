package com.mycompany.airport.manager.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Utils
import java.util.List;
import java.util.ArrayList;

public class FlightListManager {
    private List<Flight> flightList;
    
    // Constructors
    public FlightListManager() {
        this.flightList = new ArrayList<>();
    }
    
    // Add flight
    public void addFlight(Flight f) {
        flightList.add(f);
    }

    // Remove flight
    public void removeFlight(int flightNumber) {
        if(flightNumber >= 0) {
            for(int i = 0; i < flightList.size(); i++) {
                if(flightList.get(i).getFlightNumber() == flightNumber) {
                    flightList.remove(i);
                    i--;
                    System.out.println("Voo removido!");
                }
            }
        } else {
            System.out.println("Numero de voo invalido! Operacao cancelada.");
        }
    }
    
    // List all flights
    public void listFlights() {
        if(!flightList.isEmpty()) {
            System.out.println("-- Listar voos --");
            for(Flight f : flightList) {
                System.out.println(f);
            }
        } else {
            System.out.println("Nao ha nenhum voo cadastrado ate o momento.");
        }
    }
    
    // Flights with prejudice
    public String getFlightsWithPrejudice() {
        boolean found = false;
        StringBuilder sb = new StringBuilder();
                
        if(!flightList.isEmpty()) {
            sb.append("-- Listar voos com prejuizo --");
            for(Flight f : flightList) {
                if(f.checkCapacity()) {
                    sb.append("Numero do voo: ").append(f.getFlightNumber());
                    found = true;
                }
            }
            if(!found) System.out.println("Nenhum voo com prejuizo encontrado.");
        } else {
            sb.append("Nao ha nenhum voo cadastrado ate o momento.");
        }
        return sb.toString();
    }
    
    // Start a flight
    public void startFlight(int flightNumber) {
        if(flightNumber >= 0) {
            for(int i = 0; i < flightList.size(); i++) {
                Flight f = flightList.get(i);
                if(f.getFlightNumber() == flightNumber) {
                    f.setFlightStatus("Voando");
                    System.out.println("Status do voo: " + f.getFlightNumber() + " alterado para 'Voando'.");
                }
            }
        } else {
            System.out.println("Numero de voo invalido! Operação cancelada.");
        }
    }
}