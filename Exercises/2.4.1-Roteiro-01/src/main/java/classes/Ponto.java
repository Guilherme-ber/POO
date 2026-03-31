package classes;

public class Ponto {
    private double x;
    private double y;

    // Constructor
    public Ponto() {
    }
    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // To String
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Getters and Setters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    } 
    
    public double distance(Ponto p2) {
        double a = (p2.getX() - this.x) * (p2.getX() - this.x);
        double b = (p2.getY() - this.y) * (p2.getY() - this.y);
        
        return Math.sqrt(a + b);
    }
}
