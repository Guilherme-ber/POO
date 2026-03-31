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
        // Triângulo
        Ponto p1 = new Ponto(2, 2);
        Ponto p2 = new Ponto(4, 4);
        Ponto p3 = new Ponto(6, 2);
        
        Triangulo t1 = new Triangulo(p1, p2, p3);
        
        boolean isColinear = t1.isColinear(p1, p2, p3);
        double triangleArea = t1.triangleArea(p1, p2, p3);
        double perimeter = t1.perimeter(p1, p2, p3);
        String type = t1.type(p1, p2, p3);
        
        System.out.println("----- Triangulo -----");
        if(isColinear) System.out.println("O triangulo e colinear.");
        else System.out.println("O triangulo nao e colinear.");
        System.out.println("Area do triangulo: " + triangleArea + "cm²");
        System.out.println("Perimetro do triangulo: " + perimeter + "cm");
        System.out.println("Tipo do triangulo: " + type + "\n");
        
        // Quadrado
        Ponto p4 = new Ponto(0, 0);
        Ponto p5 = new Ponto(0, 2);
        Ponto p6 = new Ponto(2, 2);
        Ponto p7 = new Ponto(2, 0);
        
        Quadrado q1 = new Quadrado(p4, p5, p6, p7);
        
        System.out.println("----- Quadrado -----");
        System.out.println(q1);
    }
}
