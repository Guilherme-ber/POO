package com.mycompany.airport.manager.model;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilherme
 */
public class Airport {
    
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
        
            switch(option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    System.out.println("Voo iniciado!");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
    }
}
