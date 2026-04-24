package com.mycompany.airport.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Utils
import java.util.Scanner;

public class Main {
    public static int menu(Scanner read) {
        System.out.println("----- Aeroporto | Bem-vindo! -----");
        System.out.println("1 - Gerenciar aeroporto");
        System.out.println("2 - Gerenciar voo");
        System.out.println("3 - Acessar o sistema de fidelidade");
        System.out.println("0 - Sair do sistema");
        System.out.print("--> Escolha uma opcao: ");
        int option = read.nextInt();
        read.nextLine();
        return option;
    }
    
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        
        // Airport instance
        Airport airport = new Airport("Aeroporto Internacional de Brasilia", "Brasilia");
        
        // Flight instance
        Flight flight = new Flight();
        
        // Menu option
        int option;
       
        do {
            option = menu(read);

            switch (option) {
                case 1:
                    airport.AirportMenu();
                    break;

                case 2:
                    flight.FlightMenu();
                    break;

                case 3:
                    break;
                    
                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }
}
