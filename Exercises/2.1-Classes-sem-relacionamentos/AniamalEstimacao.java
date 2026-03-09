class Animal {
    private String especie;
    private String raca;
    private int idade;
    private double peso;
    
    // Constructors
    public Animal() {
        this.especie = "";
        this.raca = "";
        this.idade = 0;
        this.peso = 0.0;
    }
    public Animal(String especie, String raca, int idade, double peso) {
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
    }
    public void copy(Animal outra) {
        this.especie = outra.getEspecie();
        this.raca = outra.getRaca();
        this.idade = outra.getIdade();
        this.peso = outra.getPeso();
    }

    // Print
    public void print() {
        System.out.println("--- Ver Animal ---");
        System.out.println("Especie: " + this.especie);
        System.out.println("Raca: " + this.raca);
        System.out.println("Idade: " + this.idade);
        System.out.println("Peso: " + this.peso);
    }   
    
    // Getter's and Setter's
    public String getEspecie() {
        return this.especie;
    }
    public String getRaca() {
        return this.raca;
    }
    public int getIdade() {
        return this.idade;
    }
    public double getPeso() {
        return this.peso;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
}

public class AniamalEstimacao {
    public static void main(String args[]) {
        Animal a = new Animal("Cachorro", "Pincher", 4, 1.3);
        a.print();
    }
}
