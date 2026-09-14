package org.example;
import java.util.Locale;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        IO.println("Enter start - ");
        double a =  sc.nextDouble();
        IO.println("Enter end - ");
        double b =  sc.nextDouble();
        IO.println("Enter accuracy - ");
        double acc =  sc.nextDouble();

        System.out.println("function: x^3 - x - 2 = 0");

        double root = bisection(a, b, acc);
        System.out.printf("approximate root: x = %.6f%n", root);
    }
    public static double f(double x){
        return x * x * x - x - 2;
    }
    public static double bisection(double x, double y, double acc) {
        if(f(x) * f(y) >= 0){
            IO.println("similar symbols on each end of the line.");
            return 0;
        }

        double c = x;

        while((y - x) / 2.0 > acc){
            c = (y + x) / 2.0;

            if(Math.abs(f(c)) < 1e-15){
                break;
            }

            if(f(x) * f(c) < 0){
                y = c;
            }
            else {
                x = c;
            }
        }
        return c;
    }
}
