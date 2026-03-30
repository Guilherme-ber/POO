// Package
package com.mycompany.controledeestoque.exercicio6;

// Utils
import java.util.Scanner;

// Classes
import classes.ControleDeEstoque;
import classes.Produto;

/**
 *
 * @author guilh
 */
public class Main {
    public static int menu () {
        Scanner read = new Scanner(System.in);
        
        // Print menu
        System.out.println("----- Controle de Estoque -----");
        System.out.println("1. Adicionar produto");
        System.out.println("2. Remover produto");
        System.out.println("3. Listar produtos");
        System.out.println("4. Pesquisar produtos");
        System.out.println("5. Atualizar produto");
        System.out.println("6. Sair");
        
        System.out.println("-> ");
        int option = read.nextInt();
        return option;
    }

    public static void main(String[] args) {
        // Create stock controll
        ControleDeEstoque stockControll = new ControleDeEstoque();
        boolean start = true;
        
        // Call menu
        while(start) {          
        int option = menu();
            switch (option) {
                case 1 -> {
                    stockControll.addProduct();
                }
                case 2 -> {
                    stockControll.removeProduct();
                }
                case 3 -> {
                    stockControll.listProducts();
                }
                case 4 -> {
                    Produto found = stockControll.searchProduct();
                    if (found != null) {
                        System.out.println("\nProduto buscado: " + found);
                    } else {
                        System.out.println("\nProduto não encontrado.");
                    }
                }
                case 5 -> {
                    stockControll.updateQuant();
                }
                case 6 -> {
                    System.out.println("Saindo...");
                    start = false;
                }
                default -> {
                    System.out.println("Escolha uma opção válida (1 - 6).");
                }
            }
        }
    }
}
