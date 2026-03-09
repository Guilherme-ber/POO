class CasaClass {
    private String endereco;
    private double area;
    private int numQuartos;
    private double preco;

    // Constructor
    public CasaClass() {
        this.endereco = "";
        this.area = 0.0;
        this.numQuartos = 0;
        this.preco = 0.0;
    }
    public CasaClass(String endereco, double area, int numQuartos, double preco) {
        this.endereco = endereco;
        this.area = area;
        this.numQuartos = numQuartos;
        this.preco = preco;
    }
    public void copy(CasaClass outra) {
        this.endereco = outra.getEndereco();
        this.area = outra.getArea();
        this.numQuartos = outra.getNumQuartos();
        this.preco = outra.getPreco();
    }
    
    // Getter's and Setter's
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setArea(double area) {
        this.area = area;
    }
    public void setNumQuartos(int numQuartos) {
        this.numQuartos = numQuartos;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String getEndereco() {
        return endereco;
    }
    public double getArea() {
        return area;
    }
    public int getNumQuartos() {
        return numQuartos;
    }
    public double getPreco() {
        return preco;
    }
}

public class Casa {
    public static void main(String args[]) {
        CasaClass casa = new CasaClass("Praca Dr. Ultimo de Carvalho", 120, 3, 150000);
        
        System.out.println("Rua: " + casa.getEndereco());
    }
}
