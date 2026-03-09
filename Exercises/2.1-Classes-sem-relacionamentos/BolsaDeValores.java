import java.util.Scanner;

class BolsaDeValoresClass {
    private String companyName;
    private double actionsPrice;
    private double actionsQnt;
    private double diaryVariation;
    
    // Constructor
    public BolsaDeValoresClass() {
        this.companyName = "";
        this.actionsPrice = 0.0;
        this.actionsQnt = 0.0;
        this.diaryVariation = 0.0;
    }
    public BolsaDeValoresClass(String companyName, double actionsPrice, double actionsQnt, double diaryVariation) {
        this.companyName = companyName;
        this.actionsPrice = actionsPrice;
        this.actionsQnt = actionsQnt;
        this.diaryVariation = diaryVariation;
    }
    public void copy(BolsaDeValoresClass other) {
        this.companyName = other.getCompanyName();
        this.actionsPrice = other.getActionsPrice();
        this.actionsQnt = other.getActionsQnt();
        this.diaryVariation = other.getDiaryVariation();
    }
    
     // Fill
    public void fill() {
        Scanner readLine = new Scanner(System.in);
        
        System.out.println("--- Adicionar Informacoes da Bolsa de Valores ---");        
        
        System.out.println("Digite o nome da empresa: ");
        this.companyName = readLine.nextLine();
        System.out.println("Digite o preco das acoes: ");
        this.actionsPrice = readLine.nextDouble();
        System.out.println("Digite a quantidade de acoes: ");
        this.actionsQnt = readLine.nextDouble();
        System.out.println("Digite a variacao diaria: ");
        this.diaryVariation = readLine.nextDouble();
    }
    
    // Print
    public void print() {
        System.out.println("--- Ver Bolsa de Valores ---");
        System.out.println("Epresa: " + this.companyName);
        System.out.println("Preco das acoes: " + this.actionsPrice);
        System.out.println("Quantidade de acoes: " + this.actionsQnt);
        System.out.println("Variacao diaria: " + this.diaryVariation);
    }   
    
    public double calculateActivePosition() {
        return this.actionsPrice * this.actionsQnt;
    }
    
    public void showSummary() {
        System.out.println(this.companyName + ", " + this.actionsPrice + ", " + this.actionsQnt + ", " + this.diaryVariation + ", " + this.calculateActivePosition());
    }
    
    // Getter's and Setter's
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setActionsPrice(double actionsPrice) {
        this.actionsPrice = actionsPrice;
    }
    public void setActionsQnt(double actionsQnt) {
        this.actionsQnt = actionsQnt;
    }
    public void setDiaryVariation(double diaryVariation) {
        this.diaryVariation = diaryVariation;
    }
    
    public String getCompanyName() {
        return this.companyName;
    }
    public double getActionsPrice() {
        return this.actionsPrice;
    }
    public double getActionsQnt() {
        return this.actionsQnt;
    }
    public double getDiaryVariation() {
        return this.diaryVariation;
    }
}

public class BolsaDeValores {
    public static void showTotal(BolsaDeValoresClass a, BolsaDeValoresClass b, BolsaDeValoresClass c) {
        double sum = a.calculateActivePosition() + b.calculateActivePosition() + c.calculateActivePosition();
        
        System.out.println("-> Valor total da sua carteira: R$" + sum);
    }
    
    public static void main(String args[]) {
        BolsaDeValoresClass a = new BolsaDeValoresClass();
        a.fill();
        
        BolsaDeValoresClass b = new BolsaDeValoresClass();
        b.fill();
        
        BolsaDeValoresClass c = new BolsaDeValoresClass();
        c.fill();
        
        showTotal(a, b, c);
    }
}
