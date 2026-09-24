import java.util.Scanner;

public class Switch {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a: ");
        int a = sc.nextInt();
        System.out.print("Enter b: ");
        int b = sc.nextInt();

        System.out.println("Before swap: a = " + a + ", b = " + b);

        a = a + b;   // в a теперь сумма
        b = a - b;   // b = (a + b) - b = исходное a
        a = a - b;   // a = (a + b) - a = исходное b

        System.out.println("AfterSwap: a = " + a + ", b = " + b);
    }
}