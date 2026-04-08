package com.mycompany.pedidodecompra;

import models.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        Cart myCart = new Cart();
        int option = 0;

        System.out.println("----- Sistema de Pedidos -----");

        while (option != 4) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Adicionar Produto ao Carrinho");
            System.out.println("2. Remover Produto (por indice)");
            System.out.println("3. Visualizar Carrinho Atual");
            System.out.println("4. Finalizar e Gerar Pedido");
            System.out.print("Escolha uma opcao: ");
            
            option = read.nextInt();
            read.nextLine();

            switch (option) {
                case 1:
                    System.out.println("\n-- Adicionando Produto --");
                    Product p = new Product();
                    p.fill();
                    myCart.addProduct(p);
                    System.out.println("Produto adicionado com sucesso!");
                    break;

                case 2:
                    System.out.println(myCart.toString());
                    System.out.print("Digite o indice do produto para remover: ");
                    int index = read.nextInt();
                    myCart.removeProduct(index);
                    System.out.println("Operacao realizada.");
                    break;

                case 3:
                    System.out.println("\n--- Itens no seu Carrinho ---");
                    System.out.println(myCart.toString());
                    System.out.println("Subtotal: R$ " + myCart.calculateTotal());
                    break;

                case 4:
                    System.out.println("\n--- FINALIZANDO PEDIDO ---");
                    Order newOrder = new Order(100, "08/04/2026", 0, myCart);
                    System.out.println(newOrder.toString());
                    System.out.println("\nObrigado pela compra!");
                    break;

                default:
                    System.out.println("Opcao invalida! Tente novamente.");
                    break;
            }
        }
    }
}