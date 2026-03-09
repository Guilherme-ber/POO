class Animal {
    private String especie;
    private String raca;
    private int idade;
    private double peso;
    
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
}

public class AniamalEstimacao {
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
