package com.mycompany.airport.manager.manager;

// Utils
import java.util.List;
import java.util.ArrayList;

public class StopoverManager {
    private List<String> stopoverList;
    
    // Constructor
    public StopoverManager() {
        this.stopoverList = new ArrayList<>();
    }
    
    // Add stopover
    public void addStopover(String stopover) {
        stopoverList.add(stopover);
    }
    
    // Remove stopover
    public void removeStopover(String stopover) {
        if(!stopoverList.isEmpty()) {
            for(int i = 0; i < stopoverList.size(); i++) {
                if(stopoverList.get(i).equals(stopover)) {
                    stopoverList.remove(i);
                    i--;
                    System.out.println("Escala removida!");
                }
            }
        } else {
            System.out.println("O Voo nao possui nenhuma escala cadastrada ate o momento.");
        }
    }
    
    // To String
    public String toString(StringBuilder sb) {
        sb.append("\n--- Escalas ---\n");
        for (String stopover : stopoverList) {
            sb.append(stopover).append("\n");
        }
        return sb.toString();
    }

    // Getters and Setters
    public List<String> getStopoverList() {
        return stopoverList;
    }

    public void setStopoverList(List<String> stopoverList) {
        this.stopoverList = stopoverList;
    }
}