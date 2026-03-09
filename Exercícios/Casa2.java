import java.util.Scanner;

class Casa {
    private String address;
    private double area;
    private int numRooms;
    private double price;

    // Constructor
    public Casa() {
        this.address = "";
        this.area = 0.0;
        this.numRooms = 0;
        this.price = 0.0;
    }
    public Casa(String address, double area, int numRooms, double price) {
        this.address = address;
        this.area = area;
        this.numRooms = numRooms;
        this.price = price;
    }
    public void copy(Casa other) {
        this.address = other.getAddress();
        this.area = other.getArea();
        this.numRooms = other.getNumRooms();
        this.price = other.getPrice();
    }
    
     // Fill
    public void fill() {
        Scanner readLine = new Scanner(System.in);
        
        System.out.println("--- Adicionar Casa ---");        
        
        System.out.println("Digite o endereco: ");
        String iAddress = readLine.nextLine();
        System.out.println("Digite a area: ");
        double iArea = readLine.nextDouble();
        System.out.println("Digite o numero de quartos: ");
        int iNumRooms = readLine.nextInt();
        System.out.println("Digite o preco: ");
        double iPrice = readLine.nextDouble();
        
        this.address = iAddress;
        this.area = iArea;
        this.numRooms = iNumRooms;
        this.price = iPrice;
    }
    
    // Print
    public void print() {
        System.out.println("--- Ver Casa ---");
        System.out.println("Endereco: " + this.address);
        System.out.println("Area: " + this.area);
        System.out.println("Numero de quartos: " + this.numRooms);
        System.out.println("Preco: " + this.price);
    }
    
    public double calculatePrice() {
        return this.price / this.area;
    }
    
    public void showSummary() {
        System.out.println(this.address + ", " + this.area + ", " + this.numRooms + ", " + this.price + ", " + this.calculatePrice());
    }
    
    // Getter's and Setter's
    public void setAddress(String address) {
        this.address = address;
    }
    public void setArea(double area) {
        this.area = area;
    }
    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getAddress() {
        return this.address;
    }
    public double getArea() {
        return this.area;
    }
    public int getNumRooms() {
        return this.numRooms;
    }
    public double getPrice() {
        return this.price;
    }
}

public class Casa2 {
    public static void main(String args[]) {
        Casa a = new Casa();
        a.fill();
        a.print();
        a.showSummary();
    }
}
