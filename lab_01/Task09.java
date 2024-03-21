import java.util.Scanner;

public class Task09 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		
		System.out.println("Введіть коефіцієнти a, b і c із квадратного рівняння ax^2 + bx + c = 0");
		
		System.out.print("a = ");
		double a = InputScanner.nextDouble();
		
		System.out.print("b = ");
		double b = InputScanner.nextDouble();
		
		System.out.print("c = ");
		double c = InputScanner.nextDouble();
		
		InputScanner.close();

		double discrim = b*b-4*a*c;
		
		if (discrim > 0) {
			double xx1 = (-b+Math.sqrt(discrim))/(2*a);
			double xx2 = (-b-Math.sqrt(discrim))/(2*a);
			System.out.println("Корені рівняння: " + xx1 + ", " + xx2);
		} else if (discrim == 0) {
			double xx = -b / (2 * a);
			System.out.println("Знайдено один дійсний корінь рівняння: " + xx);
		} else {
			System.out.println("Рівняння не має дійсних коренів");
		}
	}
}
