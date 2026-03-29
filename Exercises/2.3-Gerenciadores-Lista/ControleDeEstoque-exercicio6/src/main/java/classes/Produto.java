package classes;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilh
 */
public class Produto {
    private String id;
    private String name;
    private int stock;

    // Construtores
    public Produto() {
    }
    public Produto(String codigo, String nome, int quantidadeEmEstoque) {
        this.id = codigo;
        this.name = nome;
        this.stock = quantidadeEmEstoque;
    }

    // Fill
    public void fill() {
        Scanner read = new Scanner(System.in);
        System.out.println("--> Adicionando produto");
        System.out.println("ID: ");
        this.id = read.nextLine();
        System.out.println("Nome: ");
        this.name = read.nextLine();
        System.out.println("Estoque: ");
        this.stock = read.nextInt();
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return "Produto{" +
                "Codigo = '" + id + '\'' +
                ", Nome = '" + name + '\'' +
                ", Quantidade em estoque = " + stock +
                '}';
    }
}
