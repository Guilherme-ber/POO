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
        double area = lado * lado;
        double perim = 4 * lado;
        return "Quadrado [Lado: " + lado + "]" +
               "\nArea: " + area + 
               "\nPerimetro: " + perim;
    }
    
    public double squareArea() {
        double height = getHeight();
        double base = getBase();
        return base * height;
    }
    
    public double perimeter() {
        double ab = this.p1.distance(this.p2);
        double bc = this.p2.distance(this.p3);
        double cd = this.p3.distance(this.p4);
        double ca = this.p4.distance(this.p1);
        
        return ab + bc + cd + ca;
    }
    
    public String type(Ponto p1, Ponto p2, Ponto p3) {
        double a = p1.distance(p2);
        double b = p2.distance(p3);
        double c = p3.distance(p1);
        
        if(a == b && b == c && c == a) return "Equilatero";
        else if(a == b || b == c || c == a) return "Isosceles";
        else if(a != b && b != c && c != a) return "Escaleno";
        else return "Sem classificação";
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

    public void setP1(Ponto p1) {
        this.p1 = p1;
    }
    public void setP2(Ponto p2) {
        this.p2 = p2;
    }
    public void setP3(Ponto p3) {
        this.p3 = p3;
    }  
}
