package com.mycompany.ex1;
import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * @author guilherme
 */
public class Ex1 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("Cliente 1 - João");
        queue.add("Cliente 2 - Maria");
        queue.add("Cliente 3 - Matheus");
        queue.add("Cliente 4 - Madalena");
        queue.add("Cliente 5 - Pedro");
        
        while(!queue.isEmpty()) {
            System.out.println("Atendendo: " + queue.poll());
            System.out.println("Há " + queue.size() + " clientes aguardando atendimento.");
        }
        System.out.println("Atendimentos finalizados!");
    }
}
