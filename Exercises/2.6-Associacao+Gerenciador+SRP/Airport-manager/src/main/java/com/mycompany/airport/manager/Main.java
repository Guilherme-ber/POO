package com.mycompany.airport.manager;

// Models
import com.mycompany.airport.manager.model.*;

// Managers
import com.mycompany.airport.manager.manager.*;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilherme
 */
public class Main {
    public static void menu() {
        System.out.println("----- Aeroporto | Bem-vindo! -----");
        System.out.println("1 - Gerenciar aeroporto");
        System.out.println("2 - Gerenciar voo");
        System.out.println("3 - Acessar o sistema de fidelidade");
        System.out.println("0 - Sair do sistema");
        System.out.print("--> Escolha uma opção: ");
    }
    
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        FidelitySystem fs = new FidelitySystem();
        
        // Airport
        Airport airport = new Airport("Aeroporto Internacional de Brasília", "Brasília");

        int option;

        do {
            menu();
            option = read.nextInt();

            switch (option) {
                case 1:
                    airport.AirportMenu();
                    break;

                case 2:
                    
                    break;

                case 3:
                    fs.FidelitySystemMenu();
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
