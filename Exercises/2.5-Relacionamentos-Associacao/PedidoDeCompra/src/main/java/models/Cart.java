package models;

// Utils
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilh
 */
public class Cart {
    private List<Product> cart;

    // Constructor
    public Cart() {
        this.cart = new ArrayList<>();
    }
    
    // Deep Copy
    public Cart(Cart other) {
        this.cart = new ArrayList<>();
        for (Product p : other.cart) {
            this.cart.add(new Product(p));
        }
    }
    
    // Add
    public void addProduct(Product p) {
        this.cart.add(new Product(p));
    }
    
    // Remove
    public void removeProduct(int i) {
        if(i >= 0 && i < cart.size()) 
            cart.remove(i);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }
    
    public int getSize() {
        return cart.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product p : cart) {
            sb.append("  - ").append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}
