package classes;

public class Quadrado {
    private Ponto p1, p2, p3, p4;
    
    public Quadrado() {
    }
    public Quadrado(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    @Override
    public String toString() {
        return "Quadrado [Lado: " + this.p1.distance(this.p2) + "]" +
               "\nArea: " + squareArea() + 
               "\nPerimetro: " + perimeter() + 
               "\nTipo: " + type();
    }
    
    public double squareArea() {
        // Base p1-p4 * Altura p1-p2
        return (this.p1.distance(this.p2) * (this.p1.distance(this.p4)));
    }
    
    public double perimeter() {
        double ab = this.p1.distance(this.p2);
        double bc = this.p2.distance(this.p3);
        double cd = this.p3.distance(this.p4);
        double da = this.p4.distance(this.p1);
        
        return ab + bc + cd + da;
    }
    
    public String type() {
        // Deve ser digitado em ordem!!
        double a = this.p1.distance(this.p2); // Lado 1
        double b = this.p2.distance(this.p3); // Lado 2
        double c = this.p3.distance(this.p4); // Lado 3
        double d = this.p4.distance(this.p1); // Lado 4
        
        boolean sameFaces = a == b && b == c && c == d && d == a;
        double hypot = this.p1.distance(this.p3);
        double hypotCalculate = Math.sqrt((d * d) + (c * c));
        
        if(sameFaces && hypot == hypotCalculate) return "Quadrado";
        else return "Não é um quadrado perfeito ou não é um quadrado.";
    }

    public Ponto getP1() {
        return p1;
    }
    public Ponto getP2() {
        return p2;
    }
    public Ponto getP3() {
        return p3;
    }
    public Ponto getP4() {
        return p4;
    }

    public void setP1(Ponto p1) {
        this.p1 = p1;
    }
    public void setP2(Ponto p2) {
        this.p2 = p2;
    }
    public void setP3(Ponto p3) {
        this.p3 = p3;
    }  
    public void setP4(Ponto p4) {
        this.p4 = p4;
    }  
}
