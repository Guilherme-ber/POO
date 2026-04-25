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
    
    // Search by flight number
    public Flight searchByFlightNumber(int flightNumber) {
        if(flightNumber >= 0) {
            for(int i = 0; i < flightList.size(); i++) {
                if(flightList.get(i).getFlightNumber() == flightNumber) {
                    return flightList.get(i);
                }
            }
        } else {
            System.out.println("Numero de voo invalido! Operacao cancelada.");
        }
        return null;
    }
    
    // List all flights
    public String listFlights(StringBuilder sb) {
        if(!flightList.isEmpty()) {
            sb.append("-- Listar voos --");
            for(Flight f : flightList) {
                sb.append(f);
            }
        } else {
            sb.append("Nao ha nenhum voo cadastrado ate o momento.");
        }
        return sb.toString();
    }
    
    // Flights with prejudice
    public String getFlightsWithPrejudice() {
        boolean found = false;
        StringBuilder sb = new StringBuilder();
                
        if(!flightList.isEmpty()) {
            sb.append("-- Listar voos com prejuizo --\n");
            for(Flight f : flightList) {
                if(!f.getPassengerManager().getPassengerList().isEmpty()) {
                    if(f.checkCapacity()) {
                        sb.append("Numero do voo: ").append(f.getFlightNumber()).append("\n");
                        found = true;
                    }
                }
            }
            if(!found) sb.append("Nenhum voo com prejuizo encontrado.");
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
    
    // Getters and Setters
    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
}