package classes;

public class Triangulo {
    private Ponto p1, p2, p3;
    private double calculateDet (Ponto p1, Ponto p2, Ponto p3) {
        return (p1.getX() * p2.getY() + p1.getY() * p3.getX() + p2.getX() * p3.getY()) - (p2.getY() * p3.getX() + p1.getX() * p3.getY() + p1.getY() * p2.getX());
    }

    public Triangulo() {
    }
    public Triangulo(Ponto p1, Ponto p2, Ponto p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    @Override
    public String toString() {
        return "Triangulo: P1=" + p1 + ", P2=" + p2 + ", P3=" + p3 + 
               "\nArea: " + triangleArea(p1, p2, p3) + 
               "\nPerimetro: " + perimeter(p1, p2, p3) + 
               "\nTipo: " + type(p1, p2, p3);
    }
    
    public boolean isColinear(Ponto p1, Ponto p2, Ponto p3) {
        double det = calculateDet(p1, p2, p3);
        return Math.abs(det) < 0.000001;
    }
    
    public double triangleArea(Ponto p1, Ponto p2, Ponto p3) {
        double det = calculateDet(p1, p2, p3);
        return Math.abs(det)/2;
    }
    
    public double perimeter(Ponto p1, Ponto p2, Ponto p3) {
        double ab = p1.distance(p2);
        double ac = p1.distance(p3);
        double bc = p2.distance(p3);
        
        return ab + ac + bc;
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
