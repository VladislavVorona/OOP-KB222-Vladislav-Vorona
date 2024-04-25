import java.util.Scanner;
import java.util.InputMismatchException;

public class Task02 {
	public static void main(String[] args) {
		Scanner InpScn = new Scanner(System.in);

		Point startPt = Point.InpPoint(InpScn, "Початку першого відрізку");
		Point endPt = Point.InpPoint(InpScn, "Кінця першого відрізку");

		Segment l1 = new Segment(startPt, endPt);

		if (l1.length() == 0) {
			System.out.println("Відрізок вироджений, його точки співпадають");
			return;
		}

		System.out.println("Довжина першого відрізку: " + l1.length());
		System.out.println("Точка середини першого відрізку: " + l1.middle());

		startPt = Point.InpPoint(InpScn, "Початку другого відрізку");
		endPt = Point.InpPoint(InpScn, "Кінця другого відрізку");

		Segment l2 = new Segment(startPt, endPt);

		if (l2.length() == 0) {
			System.out.println("Відрізок вироджений, його точки співпадають");
			return;
		}

		System.out.println("Довжина другого відрізку: " + l2.length());
		System.out.println("Точка середини другого відрізку: " + l2.middle());

		Point res = l1.intersection(l2);

		if (res != null) {
			System.out.println("Точка перетину відрізків: " + res);
		} else {
			System.out.println("Відрізки не перетинаються");
		}

		double angl = l1.anglSeek(l2);
		System.out.println("Кут між відрізками: " + Math.toDegrees(angl));
		System.out.println("Відрізки " + (l1.parralel(l2) ? "" : "не ") + "паралельні.");
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

class Segment {
	Point startPt, endPt;

	Segment(Point startPt, Point endPt) {
		this.startPt = startPt;
		this.endPt = endPt;
	}

	double length() {
		return Math.hypot(startPt.xx - endPt.xx, startPt.xy - endPt.xy);
	}

	Point middle() {
		return new Point((startPt.xx + endPt.xx) / 2, (startPt.xy + endPt.xy) / 2);
	}

	double anglSeek(Segment l2) {
		double l1Angle = Math.atan2(endPt.xy - startPt.xy, endPt.xx - startPt.xx);
		double l2Angle = Math.atan2(l2.endPt.xy - l2.startPt.xy, l2.endPt.xx - l2.startPt.xx);
		double angl = Math.abs(l1Angle - l2Angle);
		return Math.min(angl, 2 * Math.PI - angl);
	}

	boolean parralel(Segment l2) {
		double dx1 = startPt.xx - endPt.xx;
		double dy1 = startPt.xy - endPt.xy;
		double dx2 = l2.startPt.xx - l2.endPt.xx;
		double dy2 = l2.startPt.xy - l2.endPt.xy;
		return Math.abs(dx1 * dy2 - dx2 * dy1) < 1e-9;
	}

	Point intersection(Segment l2) {
		double xx1 = startPt.xx, yy1 = startPt.xy;
		double xx2 = endPt.xx, yy2 = endPt.xy;

		double xx1l2 = l2.startPt.xx, yy1l2 = l2.startPt.xy;
		double xx2l2 = l2.endPt.xx, yy2l2 = l2.endPt.xy;

		double xdem = (xx1 - xx2) * (yy1l2 - yy2l2) - (yy1 - yy2) * (xx1l2 - xx2l2);
		if (xdem == 0.0) return null;

		double xx = ((xx1 * yy2 - yy1 * xx2) * (xx1l2 - xx2l2) - (xx1 - xx2) * (xx1l2 * yy2l2 - yy1l2 * xx2l2)) / xdem;
		double xy = ((xx1 * yy2 - yy1 * xx2) * (yy1l2 - yy2l2) - (yy1 - yy2) * (xx1l2 * yy2l2 - yy1l2 * xx2l2)) / xdem;

		Point resPt = new Point(xx, xy);

		if (!seekPt(resPt, this) || !seekPt(resPt, l2)) {
			return null;
		}

		return resPt;
	}

	private boolean seekPt(Point pt, Segment l) {
		double xx1 = l.startPt.xx, yy1 = l.startPt.xy;
		double xx2 = l.endPt.xx, yy2 = l.endPt.xy;
		double xx = pt.xx, xy = pt.xy;

		double minXX = Math.min(xx1, xx2);
		double maxXX = Math.max(xx1, xx2);
		double minYY = Math.min(yy1, yy2);
		double maxYY = Math.max(yy1, yy2);

		return xx >= minXX && xx <= maxXX && xy >= minYY && xy <= maxYY;
	}
}