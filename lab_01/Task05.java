import java.util.Scanner;

public class Task05 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		
		System.out.println("Введіть висоту дерева, швидкість равлика та швидкість спуску");
		System.out.println("Висода дерева: ");
		int height = InputScanner.nextInt();
		System.out.println("Швидкість равлика: ");
		int speed = InputScanner.nextInt();
		System.out.println("Швидкість спуску равлика: ");
		int MinusSpeed = InputScanner.nextInt();

		InputScanner.close();
		
		int day = CalcDay(height, speed, MinusSpeed);
		
		if (day == -1) {
			System.out.println("Impossible");
		} else {
			System.out.println("Кількість днів, які потрібні для підняття до вершини: " + day);
		}
	}
	
	public static int CalcDay(int height, int speed, int MinusSpeed) {
		int day = 0;
		int HeightXX = 0;
		
		while (HeightXX < height) {
			HeightXX += speed;
			day++;
			
			if (HeightXX >= height) {
				return day;
			}
			
			HeightXX -= MinusSpeed;
		}
		
		return -1;
	}
}
