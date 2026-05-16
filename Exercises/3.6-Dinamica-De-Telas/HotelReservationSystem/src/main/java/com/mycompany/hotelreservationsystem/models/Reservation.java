package com.mycompany.hotelreservationsystem.models;

import java.util.Objects;

public class Reservation {
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;

    // Constructors
    public Reservation() {
    }
    public Reservation(Guest guest, Room room, String checkInDate, String checkOutDate) {
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation intent = (Reservation) o;
        return Objects.equals(guest, intent.guest) && 
               Objects.equals(room, intent.room) && 
               Objects.equals(checkInDate, intent.checkInDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guest, room, checkInDate);
    }

    @Override
    public String toString() {
        return "Reservation: " + guest.getName() + " | Room: " + room.getRoomNumber();
    }
    
    // Getters and Setters
    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

    public String getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }
}
