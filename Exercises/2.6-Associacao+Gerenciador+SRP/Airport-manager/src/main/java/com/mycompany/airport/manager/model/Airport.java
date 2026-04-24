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
    
    // Show menu
    public void AirportMenu() {
        Scanner read = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("--- Gerenciar Aeroporto ---");
            System.out.println("1 - Adicionar voo");
            System.out.println("2 - Remover voo");
            System.out.println("3 - Listar voos");
            System.out.println("4 - Voos com prejuizo");
            System.out.println("5 - Iniciar um voo");
            System.out.println("0 - Voltar");
            System.out.print("--> Escolha uma opção: ");
            option = read.nextInt();
            read.nextLine();
        
            switch(option) {
                case 1:
                    addFlight();
                    break;

                case 2:
                    removeFlight();
                    break;

                case 3:
                    listFlights();
                    break;

                case 4:
                    getFlightsWithPrejudice();
                    break;

                case 5:
                    startFlight();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
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
}
