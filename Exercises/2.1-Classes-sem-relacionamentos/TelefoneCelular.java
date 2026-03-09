class Telefone {
    private String marca;
    private String modelo;
    private double preco;
    private int memoria;
    
    // Constructors
    public Telefone() {
        this.marca = "";
        this.modelo = "";
        this.preco = 0.0;
        this.memoria = 0;
    }
    public Telefone(String marca, String modelo, double preco, int memoria) {
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
        this.memoria = memoria;
    }
    public void copy(Telefone outro) {
        this.marca = outro.getMarca();
        this.modelo = outro.getModelo();
        this.preco = outro.getPreco();
        this.memoria = outro.getMemoria();
    }

    // Getter's and Setter's
    public String getMarca() {
        return this.marca;
    }
    public String getModelo() {
        return this.modelo;
    }
    public double getPreco() {
        return this.preco;
    }  
    public int getMemoria() {
        return this.memoria;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    } 
    public void setModelo(String modelo) {
        this.modelo = modelo;
    } 
    public void setPreco(double preco) {
        this.preco = preco;
    } 
    public void setMemoria(int memoria) {
        this.memoria = memoria;
    } 
}

public class TelefoneCelular {
    public static void main(String args[]) {
        Telefone nokia, iphone;
        
        nokia = new Telefone("Nokia", "Inquebrável", 400.00, 32);
        iphone = new Telefone("Iphone Apple", "15", 2000.00, 128);
        
        System.out.println(nokia.getMarca());
        System.out.println(iphone.getMarca());
    }
}
