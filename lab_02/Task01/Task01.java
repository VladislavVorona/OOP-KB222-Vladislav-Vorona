import java.util.Scanner;
import java.util.InputMismatchException;

public class Task01 {
	public static void main(String[] args) {
		Scanner InpScn = new Scanner(System.in);

		double xk1 = 0, xb1 = 0, xk2 = 0, xb2 = 0;

		while (true) {
			try {
				System.out.println("Введіть значення K для першої прямої: ");
				xk1 = InpScn.nextDouble();

				System.out.println("Введіть значення B для першої прямої: ");
				xb1 = InpScn.nextDouble();

				System.out.println("Введіть значення K для другої прямої: ");
				xk2 = InpScn.nextDouble();

				System.out.println("Введіть значення B для другої прямої: ");
				xb2 = InpScn.nextDouble();

				break;
			} catch (InputMismatchException e) {
				System.out.println("Було введено некоректне значення, спробуйте ще раз.");
				InpScn.next();
			}
		}

		InpScn.close();

		Line l1 = new Line(xk1, xb1);
		Line l2 = new Line(xk2, xb2);

		Point res = l1.intersection(l2);

		if (res != null) {
			System.out.println("Координати точки перетину прямих: " + res);
		}
	}
}

class Point {
	private double xx, yy;

	public Point(double xx, double yy) {
		this.xx = xx;
		this.yy = yy;
	}

	@Override
	public String toString() {
		return "(" + xx + ", " + yy + ")";
	}
}

class Line {
	private double xk, xb;

	public Line(double xk, double xb) {
		this.xk = xk;
		this.xb = xb;
	}

	public Point intersection(Line l2) {
		double xk2 = l2.xk;
		double xb2 = l2.xb;

		xk = this.xk;
		xb = this.xb;

		if (xk == xk2) {
			if (xb == xb2) {
				System.out.println("Прямі співпадають, тому не можливо знайти точку перетину.");
			} else {
				System.out.println("Прямі паралельні, тому не можливо знайти точку перетину.");
			}

			return null;
		}

		double xx = (xb2 - xb) / (xk - xk2);
		double yy = xk * xx + xb;

		return new Point(xx, yy);
	}
}