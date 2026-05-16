package com.mycompany.hotelreservationsystem.managers;

import com.mycompany.hotelreservationsystem.managers.interfaces.FilePersistence;
import com.mycompany.hotelreservationsystem.models.*;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private final List<Guest> guests;
    private final List<Room> rooms;
    private final List<Reservation> reservations;
    private final FilePersistence persistence;

    // Constructor
    public HotelManager() {
        this.guests = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();

        this.persistence = new JsonSerialization(); 

        this.persistence.loadData(guests, rooms, reservations);
    }

    // Add Guest
    public boolean addGuest(Guest guest) {
        if (guests.contains(guest)) {
            return false;
        }
        guests.add(guest);
        persistence.saveData(guests, rooms, reservations);
        return true;
    }

    public List<Guest> getGuests() {
        return new ArrayList<>(guests);
    }

    // Add Room
    public boolean addRoom(Room room) {
        if (rooms.contains(room)) {
            return false;
        }
        rooms.add(room);
        persistence.saveData(guests, rooms, reservations);
        return true;
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    // Add Reservation
    public boolean addReservation(Reservation reservation) {
        if (reservations.contains(reservation)) {
            return false;
        }
        reservations.add(reservation);
        persistence.saveData(guests, rooms, reservations);
        return true;
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
    
    public void saveAll() {
        persistence.saveData(guests, rooms, reservations);
    }
}