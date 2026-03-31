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
        Ponto p1 = new Ponto(2, 2);
        Ponto p2 = new Ponto(4, 4);
        Ponto p3 = new Ponto(6, 2);
        
        Triangulo t1 = new Triangulo(p1, p2, p3);
        
        boolean isColinear = t1.isColinear(p1, p2, p3);
        double triangleArea = t1.triangleArea(p1, p2, p3);
        double perimeter = t1.perimeter(p1, p2, p3);
        String type = t1.type(p1, p2, p3);
        
        if(isColinear) System.out.println("O triangulo e colinear.");
        else System.out.println("O triangulo nao e colinear.");
        
        System.out.println("Area do triangulo: " + triangleArea + "cm²");
        System.out.println("Perimetro do triangulo: " + perimeter + "cm");
        
        System.out.println("Tipo do triangulo: " + type);
    }
}
