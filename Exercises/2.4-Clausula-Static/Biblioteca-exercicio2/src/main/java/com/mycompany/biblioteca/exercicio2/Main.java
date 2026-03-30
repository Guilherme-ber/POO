// Package
package com.mycompany.biblioteca.exercicio2;

// Classes
import classes.Livro;

/**
 *
 * @author guilh
 */
public class Main {
    public static void main(String[] args) {
        Livro l1 = new Livro("Entendendo Algoritmos", "Jose Rui", 100);
        Livro l2 = new Livro("Clean Code", "Jose Rui", 200);

        System.out.println("----- Valor Inicial -----");
        System.out.println("Livro 1: " + l1);
        System.out.println("Livro 2: " + l2);

        Livro.setLibraryName("Biblioteca Municipal de RP");

        System.out.println("\n----- Apos Alteracao -----");
        System.out.println("Livro 1: " + l1);
        System.out.println("Livro 2: " + l2);
    }
}