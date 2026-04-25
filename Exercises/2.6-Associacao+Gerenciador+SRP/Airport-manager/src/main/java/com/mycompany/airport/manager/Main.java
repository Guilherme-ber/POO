package com.mycompany.airport.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Utils
import java.util.Scanner;

public class Main {
    // Main menu
    public static int menu(Scanner read) {
        System.out.println("----- Aeroporto | Bem-vindo! -----");
        System.out.println("1 - Gerenciar aeroporto");
        System.out.println("2 - Gerenciar voo");
        System.out.println("3 - Acessar o sistema de fidelidade");
        System.out.println("0 - Sair do sistema");
        System.out.println("--> Escolha uma opcao: ");
        int option = read.nextInt();
        read.nextLine();
        return option;
    }
    
    // Airport menu
    public static void airportMenu(Scanner read) {
        int option;
        
        do {
            System.out.println("--- Gerenciar Aeroporto ---");
            System.out.println("1 - Adicionar voo");
            System.out.println("2 - Remover voo");
            System.out.println("3 - Listar voos");
            System.out.println("4 - Voos com prejuizo");
            System.out.println("5 - Iniciar um voo");
            System.out.println("0 - Voltar");
            System.out.println("--> Escolha uma opção: ");
            option = read.nextInt();
            read.nextLine();
        
            switch(option) {
                case 1 -> addFlight();

                case 2 -> removeFlight();

                case 3 -> listFlights();

                case 4 -> getFlightsWithPrejudice();

                case 5 -> startFlight();

                case 0 -> {
                }

                default -> System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }
    
    // Flight menu
    public static void flightMenu(Scanner read) {
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
            System.out.println("--> Escolha uma opção: ");
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
    
    // Fidelity System menu
    public static void fidelitySystemMenu(Scanner read) {
        int option;
        
        do {
            System.out.println("--- Sistema de Fidelidade ---");
            System.out.println("1 - Adicionar 1 ponto ao usuário");
            System.out.println("2 - Ver pontos do usuário");
            System.out.println("3 - Resgatar pontos");
            System.out.println("0 - Voltar");
            System.out.print("--> Escolha uma opção: ");
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
                        System.out.println("Não foi possível realizar o resgate.");
                    }
                }

                case 0 -> {
                }

                default -> System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }
    
    // Main
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        
        // Menu option
        int option;
       
        do {
            option = menu(read);

            switch (option) {
                case 1 -> airportMenu(read);

                case 2 -> flightMenu(read);

                case 3 -> fidelitySystemMenu(read);
                    
                case 0 -> System.out.println("Encerrando...");

                default -> System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }
}
