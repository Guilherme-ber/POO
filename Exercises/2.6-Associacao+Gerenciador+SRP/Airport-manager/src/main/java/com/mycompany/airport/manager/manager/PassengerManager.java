package com.mycompany.airport.manager.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Utils
import java.util.List;
import java.util.ArrayList;

public class PassengerManager {
    private List<Passenger> passengerList;
    
    // Constructor and Copy method
    public PassengerManager() {
        this.passengerList = new ArrayList<>();
    }
    public void copy(Flight other) {
        this.passengerList = new ArrayList<>();
        for(Passenger p : other.getPassengerManager().getPassengerList()) {
            Passenger n = new Passenger();
            n.copy(p);
            passengerList.add(n);
        }
    }
    
    // Add passenger
    public void addPassenger(Passenger p){
        passengerList.add(p);
    }
    
    // Remove passenger
    public void removePassenger(String cpf) {
        if(!passengerList.isEmpty()) {
            for (int i = 0; i < passengerList.size(); i++) {
                Passenger p = passengerList.get(i);
                if(p.getCpf().equals(cpf)) {
                    passengerList.remove(i);
                }
            }
        } else {
            System.out.println("O Voo nao possui nenhum passageiro cadastrado ate o momento.");
        }
    }
    
    // Check Capacity
    public boolean checkCapacity() {
        return passengerList.size() < 5;
    }
    
    // To String
    public String toString(StringBuilder sb) {
        sb.append("\n--- Passageiros ---\n");
        for (Passenger p : passengerList) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
    
    // Getters and Setters
    public List<Passenger> getPassengerList() {
        return passengerList;
    }
    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
}