package classes;
import java.util.HashMap;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilh
 */
public class ControleDeEstoque {
    private final HashMap<String, Produto> stock;

    public ControleDeEstoque() {
        this.stock = new HashMap<>();
    }

    public void addProduct() {
        Produto product = new Produto();
        product.fill();
        
        stock.put(product.getId(), product);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void removeProduct() {
        Scanner read = new Scanner(System.in);
        System.out.println("Informe o ID do produto: ");
        String id = read.nextLine();
        
        if (stock.remove(id) != null) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto nao encontrado!");
        }
    }

    public void updateQuant() {
        Scanner read = new Scanner(System.in);
        System.out.println("Informe o ID do produto: ");
        String id = read.nextLine();
        System.out.println("Informe o novo estoque: ");
        int newQuant = read.nextInt();
        
        Produto product = stock.get(id);
        if (product != null) {
            product.setStock(newQuant);
            System.out.println("Quantidade atualizada com sucesso!");
        } else {
            System.out.println("Produto nao encontrado!");
        }
    }

    public void listProducts() {
        if (stock.isEmpty()) {
            System.out.println("O estoque esta vazio.");
        } else {
            for (Produto product : stock.values()) {
                System.out.println(product);
            }
        }
    }

    public Produto searchProduct() {
        Scanner read = new Scanner(System.in);
        System.out.println("Informe o ID do produto: ");
        String id = read.nextLine();
        
        return stock.get(id);
    }
}
