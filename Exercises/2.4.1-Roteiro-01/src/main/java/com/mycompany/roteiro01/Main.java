// Package
package com.mycompany.roteiro01;

// Classes
import classes.Triangulo;
import classes.Quadrado;
import classes.Ponto;

// Utils
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int x1, x2, x3, x4;
        int y1, y2, y3, y4;
        
        // Triângulo
        System.out.println("---> Ponto 1");
        System.out.println("Digite o 'x': ");
        x1 = read.nextInt();
        System.out.println("Digite o 'y': ");
        y1 = read.nextInt();
        System.out.println("---> Ponto 2");
        System.out.println("Digite o 'x': ");
        x2 = read.nextInt();
        System.out.println("Digite o 'y': ");
        y2 = read.nextInt();
        System.out.println("---> Ponto 3");
        System.out.println("Digite o 'x': ");
        x3 = read.nextInt();
        System.out.println("Digite o 'y': ");
        y3 = read.nextInt();
        
        Ponto p1 = new Ponto(x1, y1);
        Ponto p2 = new Ponto(x2, y2);
        Ponto p3 = new Ponto(x3, y3);
        
        Triangulo t1 = new Triangulo(p1, p2, p3);
        
        System.out.println("----- Triangulo -----");
        System.out.println(t1);
        
        System.out.println("-------------------------");
        
        // Quadrado
        System.out.println("---> Ponto 1");
        System.out.println("Digite o 'x': ");
        x1 = read.nextInt();
        System.out.println("Digite o 'y': ");
        y1 = read.nextInt();
        System.out.println("---> Ponto 2");
        System.out.println("Digite o 'x': ");
        x2 = read.nextInt();
        System.out.println("Digite o 'y': ");
        y2 = read.nextInt();
        System.out.println("---> Ponto 3");
        System.out.println("Digite o 'x': ");
        x3 = read.nextInt();
        System.out.println("Digite o 'y': ");
        y3 = read.nextInt();
        System.out.println("---> Ponto 4");
        System.out.println("Digite o 'x': ");
        x4 = read.nextInt();
        System.out.println("Digite o 'y': ");
        y4 = read.nextInt();
        
        Ponto p4 = new Ponto(x1, y1);
        Ponto p5 = new Ponto(x2, y2);
        Ponto p6 = new Ponto(x3, y3);
        Ponto p7 = new Ponto(x4, y4);
        
        Quadrado q1 = new Quadrado(p4, p5, p6, p7);
        
        System.out.println("----- Quadrado -----");
        System.out.println(q1);
    }
}
