import java.util.Scanner;

public class ScannerTest {
    public static void main(String args[]) {
        Scanner ler = new Scanner(System.in);
        int num1;
        System.out.print("Entre com o número: ");
        num1 = ler.nextInt();
        System.out.printf("O dobro é: %d", 2*num1);
    }
}
