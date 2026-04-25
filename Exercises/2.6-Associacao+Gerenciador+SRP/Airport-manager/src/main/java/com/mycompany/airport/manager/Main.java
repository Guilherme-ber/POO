package com.mycompany.airport.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Utils
import java.util.Scanner;

public class Main {
    // Util functions
    public static int getFlightNumber(Scanner read) {
        System.out.println("Informe o numero do voo: ");
        int flightNumber = read.nextInt();
        read.nextLine();
        return flightNumber;
    }
    
    public static boolean verifyFlightExistence(Airport airport) {
        return airport.getFlightListManager().getFlightList().isEmpty();
    }

    // Main menu
    public static int menu(Scanner read) {
        System.out.println("----- Aeroporto | Bem-vindo! -----");
        System.out.println("1 - Gerenciar aeroporto");
        System.out.println("2 - Gerenciar voo");
        System.out.println("0 - Sair do sistema");
        System.out.println("--> Escolha uma opcao: ");
        int option = read.nextInt();
        read.nextLine();
        return option;
    }
    
    // Airport menu
    public static void airportMenu(Scanner read, Airport airport) {
        int option;
        
        do {
            System.out.println("--- Gerenciar Aeroporto ---");
            System.out.println("1 - Adicionar voo");
            System.out.println("2 - Remover voo");
            System.out.println("3 - Listar voos");
            System.out.println("4 - Voos com prejuizo");
            System.out.println("5 - Iniciar um voo");
            System.out.println("0 - Voltar");
            System.out.println("--> Escolha uma opcao: ");
            option = read.nextInt();
            read.nextLine();
        
            switch(option) {
                case 1 -> {
                    Flight f = new Flight();
                    f.fill(read);
                    airport.addFlight(f);
                }
                
                case 2 -> airport.removeFlight(getFlightNumber(read));
                    
                case 3 -> airport.listFlights();

                case 4 -> System.out.println(airport.getFlightsWithPrejudice());
                    
                case 5 -> {
                    // Verificar se o voo existe, se tem escala e passageiro;
                    airport.startFlight(getFlightNumber(read));
                }
                    
                case 0 -> {
                }

                default -> System.out.println("Opcao invalida!");
            }
        } while (option != 0);
    }
    
    // Flight menu
    public static void flightMenu(Scanner read, Airport airport, Flight f) {
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
            System.out.println("--> Escolha uma opcao: ");
            option = read.nextInt();
            read.nextLine();
        
            switch(option) {
                case 1 -> {
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    if(f.getPassengerManager().getPassengerList().size() < f.getMaximumPassengerCapacity()) {
                        Passenger p = new Passenger();
                        p.fill(read);
                        f.addPassenger(p);
                        System.out.println("Passageiro criado e adicionado ao voo " + f.getFlightNumber() + " com sucesso!");
                    }
                }

                case 2 -> {
                    f = airport.getFlightListManager().searchByFlightNumber(getFlightNumber(read));
                    if(!f.getPassengerManager().getPassengerList().isEmpty()) {
                        System.out.println("Digite o CPF do passageiro que sera removido: ");
                        String cpf = read.nextLine();
                        f.getPassengerManager().removePassenger(cpf);
                        System.out.println("Passageiro removido com sucesso!");
                    }
                }

                case 3 -> {
                    
                }
                    
                case 4 -> {
                    
                }

                case 5 -> {
                    
                }
                    
                case 6 -> {
                    
                }


                case 0 -> {
                }

                default -> System.out.println("Opcao invalida!");
            }
        } while (option != 0);
    }
    
    // Main
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        
        // Menu option
        int option;
       
        // Airport and Flight instance
        Airport airport = new Airport();
        airport.fill(read);
        Flight flight = new Flight();
        
        do {
            option = menu(read);

            switch (option) {
                case 1 -> airportMenu(read, airport);

                case 2 -> {
                    if(verifyFlightExistence(airport)) {
                        flightMenu(read, airport, flight);
                    } else {
                        System.out.println("Nenhum voo cadastrado. Cadastre um novo voo antes de prosseguir.");
                    }
                }    
                
                case 0 -> System.out.println("Encerrando...");

                default -> System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }
}