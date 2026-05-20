package com.mycompany.hotelreservationsystem.managers;

import com.mycompany.hotelreservationsystem.managers.interfaces.FilePersistence;
import com.mycompany.hotelreservationsystem.models.*;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class JsonSerialization implements FilePersistence {
    private static final String GUESTS_FILE = "guests.json";
    private static final String ROOMS_FILE = "rooms.json";
    private static final String RESERVATIONS_FILE = "reservations.json";

    @Override
    public void saveData(List<Guest> guests, List<Room> rooms, List<Reservation> reservations) {
        saveGuests(guests);
        saveRooms(rooms);
        saveReservations(reservations);
    }

    @Override
    public void loadData(List<Guest> guests, List<Room> rooms, List<Reservation> reservations) {
        guests.clear();
        rooms.clear();
        reservations.clear();
        
        loadGuests(guests);
        loadRooms(rooms);
        loadReservations(reservations, guests, rooms);
    }

    private void saveGuests(List<Guest> guests) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(GUESTS_FILE))) {
            writer.println("[");
            for (int i = 0; i < guests.size(); i++) {
                Guest g = guests.get(i);
                writer.print(String.format("  {\"name\":\"%s\", \"document\":\"%s\", \"contact\":\"%s\", \"address\":\"%s\"}",
                        g.getName(), g.getDocument(), g.getContact(), g.getAddress()));
                if (i < guests.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
        } catch (IOException e) {
            System.err.println("Erro ao salvar hospedes em JSON: " + e.getMessage());
        }
    }

    private void loadGuests(List<Guest> guests) {
        File file = new File(GUESTS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("{") && line.contains("}")) {
                    String name = extractJsonValue(line, "name");
                    String document = extractJsonValue(line, "document");
                    String contact = extractJsonValue(line, "contact");
                    String address = extractJsonValue(line, "address");
                    
                    guests.add(new Guest(name, document, contact, address));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar hospedes de JSON: " + e.getMessage());
        }
    }

    private void saveRooms(List<Room> rooms) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ROOMS_FILE))) {
            writer.println("[");
            for (int i = 0; i < rooms.size(); i++) {
                Room r = rooms.get(i);
                
                writer.print(String.format(Locale.US, "  {\"roomNumber\":%d, \"type\":\"%s\", \"pricePerNight\":%.2f}",
                r.getRoomNumber(), r.getType(), r.getPricePerNight()));
                
                if (i < rooms.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
        } catch (IOException e) {
            System.err.println("Erro ao salvar quartos em JSON: " + e.getMessage());
        }
    }

    private void loadRooms(List<Room> rooms) {
        File file = new File(ROOMS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("{") && line.contains("}")) {
                    int roomNumber = Integer.parseInt(extractJsonValue(line, "roomNumber"));
                    String type = extractJsonValue(line, "type");
                    double pricePerNight = Double.parseDouble(extractJsonValue(line, "pricePerNight"));
                    
                    rooms.add(new Room(roomNumber, type, pricePerNight));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar quartos de JSON: " + e.getMessage());
        }
    }

    private void saveReservations(List<Reservation> reservations) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESERVATIONS_FILE))) {
            writer.println("[");
            for (int i = 0; i < reservations.size(); i++) {
                Reservation r = reservations.get(i);
                writer.print(String.format("  {\"guestDocument\":\"%s\", \"roomNumber\":%d, \"checkInDate\":\"%s\", \"checkOutDate\":\"%s\"}",
                        r.getGuest().getDocument(), r.getRoom().getRoomNumber(), r.getCheckInDate(), r.getCheckOutDate()));
                if (i < reservations.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
        } catch (IOException e) {
            System.err.println("Erro ao salvar reservas em JSON: " + e.getMessage());
        }
    }

    private void loadReservations(List<Reservation> reservations, List<Guest> guests, List<Room> rooms) {
        File file = new File(RESERVATIONS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("{") && line.contains("}")) {
                    String guestDoc = extractJsonValue(line, "guestDocument");
                    int roomNum = Integer.parseInt(extractJsonValue(line, "roomNumber"));
                    String checkIn = extractJsonValue(line, "checkInDate");
                    String checkOut = extractJsonValue(line, "checkOutDate");

                    Guest guest = guests.stream().filter(g -> g.getDocument().equals(guestDoc)).findFirst().orElse(null);
                    Room room = rooms.stream().filter(r -> r.getRoomNumber() == roomNum).findFirst().orElse(null);

                    if (guest != null && room != null) {
                        reservations.add(new Reservation(guest, room, checkIn, checkOut));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar reservas de JSON: " + e.getMessage());
        }
    }

    private String extractJsonValue(String jsonLine, String key) {
        String pattern = "\"" + key + "\":";
        int start = jsonLine.indexOf(pattern) + pattern.length();
        
        String remainder = jsonLine.substring(start).trim();
        
        if (remainder.startsWith("\"")) {
            return remainder.substring(1, remainder.indexOf("\"", 1));
        } else {
            int end = remainder.indexOf(",");
            if (end == -1) end = remainder.indexOf("}");
            return remainder.substring(0, end).trim();
        }
    }
}