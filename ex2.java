import java.util.Scanner;

public class ex2 {
    public static void main(String args[]) {
        Scanner read = new Scanner(System.in);
        int n;
        int cont = 0;
        
        System.out.print("Digite um numero: ");
        n = read.nextInt();
        
        for(int i = 0; i <= n; i++) {
            if(i % 2 == 0) {
                cont += i;
            }
        }
        
        System.out.println("Contador: " + cont);
    }
}
