package com.mycompany.airport.manager.model;

// Utils
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight {
    private int flightNumber;
    private String destination;
    private int maximumPassengerCapacity;
    private List<Passenger> passenger;
    private List<String> stopover;
    private String flightStatus;
    
    // Constructors and Copy method
    public Flight() {
        this.flightNumber = 0;
        this.destination = "";
        this.maximumPassengerCapacity = 0;
        this.passenger = new ArrayList<>();
        this.stopover = new ArrayList<>();
        this.flightStatus = "Aguardando decolagem";
    }
    public Flight(int flightNumber, String destination, int maximumPassengerCapacity, String flightStatus) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.maximumPassengerCapacity = maximumPassengerCapacity;
        this.passenger = new ArrayList<>();
        this.stopover = new ArrayList<>();
        this.flightStatus = flightStatus;
    }
    public void copy(Flight other){
        this.flightNumber = other.getFlightNumber();
        this.destination = other.getDestination();
        this.maximumPassengerCapacity = other.getMaximumPassengerCapacity();
        this.flightStatus = other.getFlightStatus();
        
        this.passenger = new ArrayList<>();
        for(Passenger p : other.getPassenger()) {
            Passenger n = new Passenger();
            n.copy(p);
            this.passenger.add(n);
        }
    }
    
    // Show menu
    public void FlightMenu(Scanner read) {
        int option;
        
        do {
            System.out.println("--- Gerenciar Voos ---");
            System.out.println("1 - Adicionar passageiro");
            System.out.println("2 - Remover passageiro");
            System.out.println("3 - Adicionar escala");
            System.out.println("4 - Remover escala");
            System.out.println("5 - Alterar estado do voo");
            System.out.println("6 - Checar capacidade do voo");
            System.out.println("0 - Voltar");
            System.out.print("--> Escolha uma opção: ");
            option = read.nextInt();
            read.nextLine();
        
            switch(option) {
                case 1: 
                    addPoints();
                    System.out.println("1 ponto adicionado!");
                    break;

                case 2:
                    System.out.println("Pontos: " + getPoints());
                    break;

                case 3:
                    System.out.print("Quantos pontos deseja resgatar? ");
                    int points = read.nextInt();
                    read.nextLine();

                    if (redeemPoints(points)) {
                        System.out.println("Resgate realizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível realizar o resgate.");
                    }
                    break;
                    
                case 4:
                    
                    break;

                case 5:
                    
                    break;
                    
                case 6:
                    
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
        System.out.println("-- Preencher informacoes do Voo --");
        System.out.println("Numero do Voo: ");
        this.flightNumber = read.nextInt();
        System.out.println("Destino do Voo: ");
        this.destination = read.nextLine();
        System.out.println("Capacidade maxima de passageiros do Voo: ");
        this.maximumPassengerCapacity = read.nextInt();
        System.out.println("Status do Voo: ");
        this.flightStatus = read.nextLine();
    }
    
    // Add passenger
    public void addPassenger(Passenger passenger){
        this.passenger.add(passenger);
    }
    
    // Remove passenger
    public void removePassenger(List<Passenger> passenger){
        Scanner read = new Scanner(System.in);

        System.out.println("Informe o CPF do Passageiro: ");
        String cpf = read.nextLine();
        passenger.remove(cpf);
    }
    
    // Add stopover
    public void addStopover(String stopover){
        this.stopover.add(stopover);
    }
    
    // Remove stopover
    public void removeStopover(List<String> stopover){
        Scanner read = new Scanner(System.in);

        System.out.println("Informe a escala que deseja remover: ");
        String local = read.nextLine();
        stopover.remove(local);
    }
    
    // Switch flight state
    public void switchFlightState(){
        Scanner read = new Scanner(System.in);
         System.out.println("Selecione o Estado de voo");
         System.out.println("1-aguardando decolagem \n2-voando \n3-concluído");
         int option = read.nextInt();
         read.nextLine();
         
         for(int i = 0; i >= 0; i++) {
            switch(option) {
                case 1:
                    setFlightStatus("aguardando decolagem");
                    break;
                case 2:
                    setFlightStatus("voando");
                    break;
                case 3:
                    setFlightStatus("concluído");
                    break;
            }
        }  
    }
    
    // Check minimal capacity (5 passengers)
    public boolean checkCapacity(){
        return true;
    }
    
    // To String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== Dados do Voo ===\n");
        sb.append("Número do voo: ").append(flightNumber).append("\n");
        sb.append("Destino: ").append(destination).append("\n");
        sb.append("Capacidade máxima: ").append(maximumPassengerCapacity).append("\n");

        sb.append("Estado do voo: ").append(flightStatus).append("\n");

        sb.append("\n--- Passageiros ---\n");
        if (passenger.isEmpty()) {
            sb.append("Nenhum passageiro cadastrado.\n");
        } else {
            for (Passenger p : passenger) {
                sb.append(p.toString()).append("\n");
            }
        }

        sb.append("\n--- Escalas ---\n");
        if (stopover.isEmpty()) {
            sb.append("Sem escalas.\n");
        } else {
            for (String escala : stopover) {
                sb.append(escala).append("\n");
            }
        }

        sb.append("\nTotal de passageiros: ").append(passenger.size());

        return sb.toString();
    }

    // Getters and Setters
    public int getFlightNumber() {
        return flightNumber;
    }
    public String getDestination() {
        return destination;
    }
    public int getMaximumPassengerCapacity() {
        return maximumPassengerCapacity;
    }
    public List<Passenger> getPassenger() {
        return passenger;
    }
    public List<String> getStopover() {
        return stopover;
    }
    public String getFlightStatus() {
        return flightStatus;
    }
    
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setMaximumPassengerCapacity(int maximumPassengerCapacity) {
        this.maximumPassengerCapacity = maximumPassengerCapacity;
    }
    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }
    public void setStopover(List<String> stopover) {
        this.stopover = stopover;
    }
    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }
}