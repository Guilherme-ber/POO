class Aluno {
    private String nome;
    private int idade;
    private String matricula;
    private int anoIngresso;
    
    // Constructor
    public Aluno() {
        this.nome = "";
        this.idade = 0;
        this.matricula = "";
        this.anoIngresso = 0;
    }
    public Aluno(String nome, int idade, String matricula, int anoIngresso) {
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
        this.anoIngresso = anoIngresso;
    }
    public void copy(Aluno outra) {
        this.nome = outra.getNome();
        this.idade = outra.getIdade();
        this.matricula = outra.getMatricula();
        this.anoIngresso = outra.getAnoIngresso();
    }

    // Getter's and Setter's
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public String getMatricula() {
        return matricula;
    }
    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }
}

public class Alunos {
    public static void main(String args[]) {
        Aluno Guilherme = new Aluno("Guilherme", 19, "2026-1", 2026);
        
        System.out.println("Nome: " + Guilherme.getNome());
    }
}
