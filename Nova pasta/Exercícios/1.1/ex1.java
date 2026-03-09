import java.util.Scanner;

public class ex1 {
    public static void main(String args[]) {
        Scanner read = new Scanner (System.in);
        int num1;
        int num2;
        
        System.out.print("Digite um numero: ");
        num1 = read.nextInt();
        System.out.print("Digite outro numero: ");
        num2 = read.nextInt();
        
        if(num1 > num2) {
            System.out.println("O numero " + num1 + " eh maior que " + num2);
        } else if (num2 > num1) {
            System.out.println("O numero " + num2 + " eh maior que " + num1);
        } else {
            System.out.println("Os numeros sao iguais");
        }
    }
}
