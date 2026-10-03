import java.util.Locale;

public class SimpleIterationsMethod {

    private static final double DEFAULT_A = 1.0;
    private static final double DEFAULT_B = 2.0;
    private static final double DEFAULT_EPS = 0.0001;
    private static final double DEFAULT_X0 = 1.5;
    private static final int DEFAULT_MAX_ITER = 100;

    public static void main(String[] args) {
        double a = DEFAULT_A;
        double b = DEFAULT_B;
        double eps = DEFAULT_EPS;
        double x0 = DEFAULT_X0;
        int maxIter = DEFAULT_MAX_ITER;

        // Если переданы аргументы командной строки:
        // java SimpleIteration a b eps x0 maxIter
        if (args.length >= 5) {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            eps = Double.parseDouble(args[2]);
            x0 = Double.parseDouble(args[3]);
            maxIter = Integer.parseInt(args[4]);
        } else {
            System.out.println("Using default values:");
            System.out.println("a = 1, b = 2, eps = 0.0001, x0 = 1.5, maxIter = 100");
            System.out.println();
        }

        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }

        if (eps <= 0) {
            throw new IllegalArgumentException("Accuracy eps 0 should be greater than zero");
        }

        if (maxIter <= 0) {
            throw new IllegalArgumentException("Iterations amount maxIter should be greater than 0");
        }

        if (x0 < a || x0 > b) {
            x0 = (a + b) / 2.0;
            System.out.printf(Locale.US, "Starting approximation. Selected x0 = %.10f%n", x0);
        }

        IterationResult result = simpleIteration(x0, eps, maxIter);

        System.out.printf(Locale.US, "Length: [%.6f; %.6f]%n", a, b);
        System.out.printf(Locale.US, "Accuracy: %.10f%n", eps);
        System.out.printf(Locale.US, "Starting approximation: %.10f%n", x0);
        System.out.printf(Locale.US, "Approximate root: %.10f%n", result.x);
        System.out.printf(Locale.US, "Iterations amount: %d%n", result.iterations);
        System.out.printf(Locale.US, "Check f(x): %.10e%n", f(result.x));
        System.out.printf(Locale.US, "Accuracy achieved: %s%n", result.converged ? "+" : "-");
    }

    /**
     * Метод простых итераций для уравнения x = phi(x).
     *
     * @param x0      начальное приближение
     * @param eps     заданная точность
     * @param maxIter максимальное число итераций
     * @return результат: корень, число итераций, признак достижения точности
     */
    public static IterationResult simpleIteration(double x0, double eps, int maxIter) {
        double x = x0;
        int iterations = 0;
        boolean converged = false;

        for (int i = 1; i <= maxIter; i++) {
            double xNext = phi(x);
            iterations = i;

            if (Math.abs(xNext - x) < eps) {
                x = xNext;
                converged = true;
                break;
            }

            x = xNext;
        }

        return new IterationResult(x, iterations, converged);
    }


     //Итерационная функция: x = cbrt(x + 2).
    private static double phi(double x) {
        return Math.cbrt(x + 2.0);
    }


     //Исходная функция: f(x) = x^3 - x - 2.
    private static double f(double x) {
        return x * x * x - x - 2.0;
    }

    public static final class IterationResult {
        public final double x;
        public final int iterations;
        public final boolean converged;

        public IterationResult(double x, int iterations, boolean converged) {
            this.x = x;
            this.iterations = iterations;
            this.converged = converged;
        }
    }
}