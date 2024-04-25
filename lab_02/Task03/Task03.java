import java.util.Scanner;
import java.util.InputMismatchException;

public class Task03 {
	public static void main(String[] args) {
		Scanner InpScn = new Scanner(System.in);
		Point xp1 = Point.InpPoint(InpScn, "вершини 1");
		Point xp2 = Point.InpPoint(InpScn, "вершини 2");
		Point xp3 = Point.InpPoint(InpScn, "вершини 3");

		Triangle triang = new Triangle(xp1, xp2, xp3);
		System.out.println("Площа трикутнику: " + triang.area());
		System.out.println("Периметр трикутнику: " + triang.perim());
		System.out.println("Центроїд трикутнику: " + triang.cent());

		for (int xx = 0; xx < 3; xx++) {
			System.out.println("Висота від вершини " + (xx + 1) + ": " + triang.height(xx));
		}

		System.out.println("Радіус вписаного кола: " + triang.radIC());
		System.out.println("Радіус описаного кола: " + triang.radCC());

		System.out.println("Кути трикутника:");
		System.out.println("Кут 1: " + triang.angl(xp1, xp2, xp3));
		System.out.println("Кут 2: " + triang.angl(xp2, xp1, xp3));
		System.out.println("Кут 3: " + triang.angl(xp2, xp3, xp1));

		System.out.println("Це прямокутний трикутник? " + triang.right());
	}
}

class Point {
	double xx, xy;

	Point(double xx, double xy) {
		this.xx = xx;
		this.xy = xy;
	}

	static Point InpPoint(Scanner InpScn, String ptName) {
		while (true) {
			try {
				System.out.print("Введіть значення X для " + ptName + ": ");
				double xx = InpScn.nextDouble();
				System.out.print("Введіть значення Y для " + ptName + ": ");
				double xy = InpScn.nextDouble();
				return new Point(xx, xy);
			} catch (InputMismatchException e) {
				System.out.println("Було введено некоректне значення, спробуйте ще раз.");
				InpScn.nextLine();
			}
		}
	}

	@Override
	public String toString() {
		return "(" + xx + "; " + xy + ")";
	}
}

class Triangle {
	Point xp1, xp2, xp3;

	Triangle(Point xp1, Point xp2, Point xp3) {
		if (!isValidTriangle(xp1, xp2, xp3)) {
			throw new IllegalArgumentException("Такий трикутник не може існувати");
		}
		this.xp1 = xp1;
		this.xp2 = xp2;
		this.xp3 = xp3;
	}

	private boolean isValidTriangle(Point xp1, Point xp2, Point xp3) {
		return !xp1.equals(xp2) && !xp2.equals(xp3) && !xp3.equals(xp1) && !areCollinear(xp1, xp2, xp3);
	}

	private boolean areCollinear(Point xp1, Point xp2, Point xp3) {
		return Math.abs((xp2.xy - xp1.xy) * (xp3.xx - xp1.xx) - (xp3.xy - xp1.xy) * (xp2.xx - xp1.xx)) < 1e-9;
	}

	double area() {
	    double xs1 = distance(xp1, xp2);
	    double xs2 = distance(xp2, xp3);
	    double xs3 = distance(xp3, xp1);
	    double xp = (xs1 + xs2 + xs3) / 2.0;
	    return Math.sqrt(xp * (xp - xs1) * (xp - xs2) * (xp - xs3));
	}

	double perim() {
		double xs1 = distance(xp1, xp2);
		double xs2 = distance(xp2, xp3);
		double xs3 = distance(xp3, xp1);
		return xs1 + xs2 + xs3;
	}

	double height(int xx) {
		Point[] point = {xp1, xp2, xp3};
		Point oPoint = point[(xx + 1) % 3];
		Point aPoint = point[(xx + 2) % 3];
		double res = distance(oPoint, aPoint);
		return 2 * area() / res;
	}


	double radIC() {
		return 2 * area() / perim();
	}

	double radCC() {
		double xs1 = distance(xp1, xp2);
		double xs2 = distance(xp2, xp3);
		double xs3 = distance(xp3, xp1);
		return (xs1 * xs2 * xs3) / (4 * area());
	}

	boolean right() {
	    double angl1 = angl(xp1, xp2, xp3);
	    double angl2 = angl(xp2, xp1, xp3);
	    double angl3 = angl(xp3, xp1, xp2);

	    return (Math.abs(angl1 - 90) < 1e-9) || (Math.abs(angl2 - 90) < 1e-9) || (Math.abs(angl3 - 90) < 1e-9);
	}

	public double angl(Point xp1, Point xp2, Point xp3) {
	    double xs1 = distance(xp1, xp2);
	    double xs2 = distance(xp2, xp3);
	    double xs3 = distance(xp3, xp1);

	    double cAngl = (Math.pow(xs1, 2) + Math.pow(xs2, 2) - Math.pow(xs3, 2)) / (2 * xs1 * xs2);
	    double aRad = Math.acos(cAngl);
	    return Math.toDegrees(aRad);

	}

	private double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.xx - p1.xx, 2) + Math.pow(p2.xy - p1.xy, 2));
	}

	Point cent() {
		double cx = (xp1.xx + xp2.xx + xp3.xx) / 3.0;
		double cy = (xp1.xy + xp2.xy + xp3.xy) / 3.0;
		return new Point(cx, cy);
	}
}