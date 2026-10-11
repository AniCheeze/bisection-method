import java.util.Locale;

public class Newton{

    public static void main(String[] args) {
        // Значения по умолчанию
        double a = 0.0;
        double b = 1.0;
        double eps = 0.0001;
        double x0 = 0.5;
        int maxIter = 100;

        // Можно передать аргументы командной строки:
        // java NewtonRoot a b eps x0 maxIter
        if (args.length >= 1) a = Double.parseDouble(args[0]);
        if (args.length >= 2) b = Double.parseDouble(args[1]);
        if (args.length >= 3) eps = Double.parseDouble(args[2]);
        if (args.length >= 4) x0 = Double.parseDouble(args[3]);
        if (args.length >= 5) maxIter = Integer.parseInt(args[4]);

        System.out.printf("Length: [%.6f, %.6f]%n", a, b);
        System.out.printf("accuracy eps: %.6f%n", eps);
        System.out.printf("Starting closeness x0: %.6f%n", x0);
        System.out.printf("Max iterations amount: %d%n%n", maxIter);

        try {
            double root = newton(x0, eps, maxIter);

            System.out.printf("%napproximate root: %.8f%n", root);
            System.out.printf("Check f(root) = %.10e%n", f(root));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Исходная функция f(x) = 2x - 1/tan(x)
    public static double f(double x) {
        double tan = Math.tan(x);

        if (Math.abs(tan) < 1e-15) {
            throw new IllegalArgumentException("tan(x) close to 0, function isn't defined where x = " + x);
        }

        return 2.0 * x - 1.0 / tan;
    }

    // Производная f'(x) = 2 + 1/sin^2(x)
    public static double df(double x) {
        double sin = Math.sin(x);

        if (Math.abs(sin) < 1e-15) {
            throw new IllegalArgumentException("sin(x) close to 0, proizvodnaya isn't defined where x = " + x);
        }

        return 2.0 + 1.0 / (sin * sin);
    }

    // Метод Ньютона
    public static double newton(double x0, double eps, int maxIter) {
        double x = x0;

        for (int i = 1; i <= maxIter; i++) {
            double fx = f(x);
            double dfx = df(x);

            if (Math.abs(dfx) < 1e-15) {
                throw new IllegalArgumentException("Proizvodnaya close to 0, cannot continue.");
            }

            double xNext = x - fx / dfx;

            System.out.printf(
                    "Iteration %2d: x = %.10f, f(x) = %.10e, |x_next - x| = %.10e%n",
                    i, xNext, f(xNext), Math.abs(xNext - x)
            );

            if (Math.abs(xNext - x) < eps) {
                return xNext;
            }

            x = xNext;
        }

        System.out.printf( "Max iterations amount reached: %d%n", maxIter);
        return x;
    }
}