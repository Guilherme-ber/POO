package com.mycompany.airport.manager.model;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilherme
 */
public class FidelitySystem {
    private int accumulatedPoints;
    
    // Constructors
    public FidelitySystem() {
        this.accumulatedPoints = 0;
    }
    public FidelitySystem(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }
    
    // Add points
    public void addPoints() {
        if(accumulatedPoints >= 0) this.accumulatedPoints++;
    }
    
    // Verify/Show points
    public int getPoints() {
        return accumulatedPoints;
    }
    
    // Show menu
    public void FidelitySystemMenu() {
        Scanner read = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("--- Sistema de Fidelidade ---");
            System.out.println("1 - Adicionar 1 ponto");
            System.out.println("2 - Ver pontos");
            System.out.println("3 - Resgatar pontos");
            System.out.println("0 - Voltar");
            System.out.print("--> Escolha uma opção: ");
            option = read.nextInt();
        
            switch(option) {
                case 1: 
                    fs.addPoints();
                    System.out.println("1 ponto adicionado!");
                    break;

                case 2:
                    System.out.print("Quantos pontos deseja resgatar? ");
                    int pontos = read.nextInt();

                    if (fs.redeemPoints(pontos)) {
                        System.out.println("Resgate realizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível realizar o resgate.");
                    }
                    break;

                case 3:
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
    }
    
    // Rescue points
    public boolean redeemPoints(int pointsForRedeem) {
        if (pointsForRedeem <= 0 || pointsForRedeem > this.accumulatedPoints) {
            return false;
        } else {
            this.accumulatedPoints -= pointsForRedeem;
            return true;
        }
    }
}