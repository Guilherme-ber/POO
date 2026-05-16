package com.mycompany.hotelreservationsystem.managers.interfaces;

import com.mycompany.hotelreservationsystem.models.*;
import java.util.List;

public interface FilePersistence {
    void saveData(List<Guest> guests, List<Room> rooms, List<Reservation> reservations);
    
    void loadData(List<Guest> guests, List<Room> rooms, List<Reservation> reservations);
}
