package com.mycompany.airport.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Utils
import java.util.Scanner;

public class Main {
    // Get flight number
    public static int getFlightNumber(Scanner read) {
        System.out.println("Informe o numero do voo para remocao: ");
        int flightNumber = read.nextInt();
        read.nextLine();
        return flightNumber;
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
                    
                case 5 -> airport.startFlight(getFlightNumber(read));
                    
                case 0 -> {
                }

                default -> System.out.println("Opcao invalida!");
            }
        } while (option != 0);
    }
    
    // Flight menu
    public static void flightMenu(Scanner read, Airport airport) {
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
                    addPoints();
                    System.out.println("1 ponto adicionado!");
                }

                case 2 -> System.out.println("Pontos: " + getPoints());

                case 3 -> {
                    System.out.print("Quantos pontos deseja resgatar? ");
                    int points = read.nextInt();
                    read.nextLine();

                    if (redeemPoints(points)) {
                        System.out.println("Resgate realizado com sucesso!");
                    } else {
                        System.out.println("Nao foi possivel realizar o resgate.");
                    }
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
       
        // Airport instance
        Airport airport = new Airport();
        airport.fill(read);
        
        do {
            option = menu(read);

            switch (option) {
                case 1 -> airportMenu(read, airport);

                case 2 -> flightMenu(read, airport);
                    
                case 0 -> System.out.println("Encerrando...");

                default -> System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }
}