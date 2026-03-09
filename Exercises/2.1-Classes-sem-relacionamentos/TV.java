class Televisao {
    private String marca;
    private double tamanho;
    private String resolucao;
    private double preco;

    public Televisao() {
        this.marca = "";
        this.tamanho = 0.0;
        this.resolucao = "";
        this.preco = 0.0;
    }

    public Televisao(String marca, double tamanho, String resolucao, double preco) {
        this.marca = marca;
        this.tamanho = tamanho;
        this.resolucao = resolucao;
        this.preco = preco;
    }
    
    public void copy(Televisao outra) {
        this.marca = outra.getMarca();
        this.tamanho = outra.getTamanho();
        this.resolucao = outra.getResolucao();
        this.preco = outra.getPreco();
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }
    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String getMarca() {
        return this.marca;
    }
    public double getTamanho() {
        return this.tamanho;
    }
    public String getResolucao() {
        return this.resolucao;
    }
    public double getPreco() {
        return this.preco;
    }
}

public class TV {
    public static void main(String args[]) {
        Televisao philips = new Televisao("Philips", 42, "1980x1080", 1500);
        Televisao philco = new Televisao();
        philco.copy(philips);
        
        System.out.println("Marca: " + philips.getMarca() + " -- R$" + philips.getPreco());
        System.out.println("Marca: " + philco.getMarca() + " -- R$" + philco.getPreco());
    }
}
