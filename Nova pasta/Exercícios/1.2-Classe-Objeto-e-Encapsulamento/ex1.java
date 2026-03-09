class Roda {
    private String marca;
    private double raio;
    private double pesoMax;

    // Constructor
    public Roda() {
        this.marca = "";
        this.raio = 0.0;
        this.pesoMax = 0.0;
    }
    public Roda(String marca, double raio, double pesoMax) {
        this.marca = marca;
        this.raio = raio;
        this.pesoMax = pesoMax;
    }
    
    // Getter's and Setter's
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return this.marca;
    }
    public void setRaio(double raio) {
        this.raio = raio;
    }
    public double getRaio() {
        return this.raio;
    }
    public void setPesoMax(double pesoMax) {
        this.pesoMax = pesoMax;
    }
    public double getPesoMax() {
        return this.pesoMax;
    }
}

class Produto {
    private int id;
    private String nome;
    private double preco;
    
    // Constructor
    public Produto() {
        this.id = 0;
        this.nome = "";
        this.preco = 0.0;
    }
    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    
    // Getter's and Setter's
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getPreco() {
        return this.preco;
    }
}

public class ex1 {
    public static void main(String args[]) {
        Roda aro12 = new Roda("Bmw", 20.50, 10);
        Roda aro17 = new Roda("Pirelli", 17.0, 8);
        
        Produto arroz = new Produto(1, "Arroz", 10.00);
        Produto feijao = new Produto(2, "Feijão", 8.50); 
        
        System.out.println("Marca da roda: " + aro12.getMarca());
        System.out.println("Raio da roda: " + aro12.getRaio() + "cm");
        System.out.println("Peso max. da roda: " + aro12.getPesoMax() + "Kg");
        
        System.out.println("ID do produto: " + arroz.getId());
        System.out.println("Produto: " + arroz.getNome());
        System.out.println("Preco do produto: R$" + arroz.getPreco());
    }
}
