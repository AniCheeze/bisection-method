def f(x):
    return x**3 - x - 2


def bisection(a, b, eps, max_iter=100):
    fa = f(a)
    fb = f(b)

    if fa == 0:
        return a, 0
    if fb == 0:
        return b, 0

    if fa * fb > 0:
        raise ValueError("На концах отрезка функция имеет одинаковые знаки.")

    print(f"{'i':>3} {'a':>12} {'b':>12} {'c':>12} {'f(c)':>14}")

    for i in range(1, max_iter + 1):
        c = (a + b) / 2.0
        fc = f(c)

        print(f"{i:3d} {a:12.8f} {b:12.8f} {c:12.8f} {fc:14.6e}")

        if abs(b - a) / 2.0 < eps or fc == 0:
            return c, i

        if fa * fc < 0:
            b = c
            fb = fc
        else:
            a = c
            fa = fc

    return (a + b) / 2.0, max_iter


def main():
    try:
        a = float(input("Введите левую границу интервала a: "))
        b = float(input("Введите правую границу интервала b: "))
        eps = float(input("Введите точность eps, например 1e-6: "))
    except ValueError:
        print("Ошибка: необходимо ввести числа.")
        return

    if a > b:
        a, b = b, a

    try:
        root, iterations = bisection(a, b, eps)
    except ValueError as error:
        print("Ошибка:", error)
        return

    print("\nРезультат:")
    print(f"Приближённый корень: x = {root:.10f}")
    print(f"Значение функции: f(x) = {f(root):.6e}")
    print(f"Количество итераций: {iterations}")


if __name__ == "__main__":
    main()