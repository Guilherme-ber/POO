package com.mycompany.proprietario.carro;

import com.mycompany.proprietario.carro.model.*;
import java.util.Scanner;

/**
 *
 * @author guilherme
 */
public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.println("=== Cadastro de Proprietário ===");
        System.out.print("Nome: ");
        String nome = read.nextLine();
        System.out.print("CPF: ");
        String cpf = read.nextLine();
        System.out.print("Email: ");
        String email = read.nextLine();

        // 1. Criamos o Proprietário (que já nasce com seu CarManager interno)
        Owner proprietario = new Owner(nome, cpf, email);

        String continuarCarro = "s";
        while (continuarCarro.equalsIgnoreCase("s")) {
            System.out.println("\n--- Adicionando um Carro ---");
            System.out.print("Fabricante: ");
            String fab = read.nextLine();
            System.out.print("Modelo: ");
            String mod = read.nextLine();
            System.out.print("Cor: ");
            String cor = read.nextLine();

            // 2. Criamos o Carro (que já nasce com seu WheelManager interno)
            Car novoCarro = new Car(fab, mod, cor);

            // Adicionando as Rodas (geralmente 4)
            for (int i = 1; i <= 4; i++) {
                System.out.println("Dados da Roda " + i + ":");
                System.out.print("  Raio: ");
                double raio = read.nextDouble();
                read.nextLine(); // Limpa buffer
                System.out.print("  Material: ");
                String mat = read.nextLine();
                System.out.print("  Cor da Roda: ");
                String corRoda = read.nextLine();

                Wheel roda = new Wheel(raio, mat, corRoda);
                novoCarro.addWheel(roda); // Delegação: Carro -> WheelManager
            }

            // 3. Adicionamos o carro pronto ao proprietário
            proprietario.addCar(novoCarro); // Delegação: Owner -> CarManager

            System.out.print("\nDeseja cadastrar outro carro para este dono? (s/n): ");
            continuarCarro = read.nextLine();
        }

        // 4. Impressão Final usando a Cascata de toString
        System.out.println("\n========================================");
        System.out.println("RESUMO DO PROPRIETÁRIO E SEUS BENS");
        System.out.println("========================================");
        System.out.println(proprietario.toString()); 
        
        read.close();
    }
}