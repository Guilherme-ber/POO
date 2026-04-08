package com.mycompany.futebol;
import models.*;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilh
 */
public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        // Create coachs
        Coach t1 = new Coach("Renato Gaucho", "Brasileiro", 1970);
        Coach t2 = new Coach("Abel Ferreira", "Portugues", 1990);

        // Add teams
        Team team1 = new Team("Vasco da Gama", "Rio de Janeiro", 1898);
        team1.takeCoach(t1);

        Team team2 = new Team("Palmeiras", "Sao Paulo", 1914);
        team2.takeCoach(t2);

        System.out.println("----- Situacao Inicial -----");
        System.out.println(team1);
        System.out.println("\n" + team2);

        System.out.println("\n---- Mudanca de Tecnico ----");
        System.out.print("Digite o nome do novo tecnico para o " + team1.getName() + ": ");
        String newName = reader.nextLine();
        System.out.print("Digite a nacionalidade do novo tecnico para o " + team1.getName() + ": ");
        String newNationality = reader.nextLine();
        System.out.print("Digite o ano de nascimento do novo tecnico para o " + team1.getName() + ": ");
        int newBirthYear = reader.nextInt();
        
        Coach t3 = new Coach(newName, newNationality, newBirthYear);
        team1.takeCoach(t3);

        System.out.println("\n--- Situacao Apos Troca ---");
        System.out.println(team1);
        System.out.println("\n" + team2);
    }
}