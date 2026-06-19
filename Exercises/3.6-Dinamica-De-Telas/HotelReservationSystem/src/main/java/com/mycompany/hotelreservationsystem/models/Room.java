package com.mycompany.hotelreservationsystem.models;

import java.util.Objects;

public class Room {
    private int roomNumber;
    private String type;
    private double pricePerNight;

    // Constructors
    public Room() {
    }
    public Room(int roomNumber, String type, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return "Quarto nº:" + roomNumber + " (" + type + ") - $" + pricePerNight;
    }
    
    // Getters and Setters
    public int getRoomNumber() { 
        return roomNumber; 
    }
    public String getType() { 
        return type; 
    }
    public double getPricePerNight() { 
        return pricePerNight; 
    }
    
    public void setRoomNumber(int roomNumber) { 
        this.roomNumber = roomNumber; 
    }
    public void setType(String type) { 
        this.type = type; 
    }
    public void setPricePerNight(double pricePerNight) { 
        this.pricePerNight = pricePerNight; 
    }
}
