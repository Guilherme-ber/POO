package com.mycompany.ex2;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author guilherme
 */
public class Ex2 {
    public static void main(String[] args) {
        HashMap<String, Double> shelf = new HashMap<>();
        
        shelf.put("Arroz", 10.0);
        shelf.put("Feijao", 8.0);
        shelf.put("Banana", 5.0);
        shelf.put("Abacate", 14.0);
        shelf.put("Peixe", 25.0);
        
        // a)
        for(Map.Entry<String, Double> product : shelf.entrySet()) {
            System.out.println(product.getKey() + ": R$" + product.getValue());
        }
        
        System.out.println("-------------------------");
        
        // b)
        shelf.put("Arroz", 12.00);
        for(Map.Entry<String, Double> product : shelf.entrySet()) {
            System.out.println(product.getKey() + ": R$" + product.getValue());
        }
        
        System.out.println("-------------------------");
        
        // c)
        if(shelf.containsKey("Banana") == true) {
            System.out.println("O produto Banana existe! Iniciando remocao.");
            shelf.remove("Banana");
        } else {
            System.out.println("O produto Banana não existe na estante.");
        }
        for(Map.Entry<String, Double> product : shelf.entrySet()) {
            System.out.println(product.getKey() + ": R$" + product.getValue());
        }
    }
}
