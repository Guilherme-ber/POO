package models;

/**
 *
 * @author guilh
 */
public class Order {
    private int number;
    private String date;
    private int quant;
    private Cart cart;

    // Constructors
    public Order() {
    }
    public Order(int number, String date, int quant, Cart cart) {
        this.number = number;
        this.date = date;
        this.quant = quant;
        this.cart = new Cart(cart);
    }

    // Copy
    public void deepCopy(Order other) {
        this.number = other.getNumber();
        this.date = other.getDate();
        this.quant = other.getQuant();
        this.cart = new Cart(other.getCart());
    }

    // Print
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-> Pedido: \n");
        sb.append("1. Numero: ").append(this.number).append("\n");
        sb.append("2. Data: ").append(this.date).append("\n");
        sb.append("3. Quantidade de itens: ").append(this.getCart().getSize()).append("\n");
        sb.append("4. Produtos: \n").append(this.cart.toString()).append("\n");
        
        sb.append("Total: R$ ").append(this.cart.calculateTotal());

        return sb.toString();
    }    
    
    // Getter's and Setter's
    public int getNumber() {
        return number;
    }
    public String getDate() {
        return date;
    }
    public int getQuant() {
        return quant;
    }
    public Cart getCart() {
        return cart;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setQuant(int quant) {
        this.quant = quant;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}