package models;

// Utils
import java.util.Scanner;

/**
 *
 * @author guilh
 */
public class Product {
    private String name;
    private String category;
    private double price;

    // Constructors
    public Product() {
    }
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
    public Product(Product other) {
        this.name = other.getName();
        this.category = other.getCategory();
        this.price = other.getPrice();
    }
    
    // Copy
    public void copy(Product other) {
        this.name = other.getName();
        this.category = other.getCategory();
        this.price = other.getPrice();
    }
    
    // Fill
    public void fill () {
        Scanner read = new Scanner(System.in);
        System.out.print("Nome do produto: ");
        this.name = read.nextLine();

        System.out.print("Categoria: ");
        this.category = read.nextLine();

        System.out.print("Preco: ");
        this.price = read.nextDouble();
        read.nextLine();
    }
    
    // Print
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-> Produto: \n");
        sb.append("1. Nome: ").append(this.name).append("\n");
        sb.append("2. Categoria: ").append(this.category).append("\n");
        sb.append("3. Preco Unitario: ").append(this.price).append("\n");

        return sb.toString();
    }
    
    // Getter's and Setter's
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrice(double price) {
        this.price = price;
    } 
}
