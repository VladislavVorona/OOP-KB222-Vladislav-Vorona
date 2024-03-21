import java.util.Scanner;

public class Task02 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		
		System.out.println("Вкажіть кількість секунд:");
		int InputValue = InputScanner.nextInt();

		while (InputValue < 0) {
			System.out.println("Було вказано від'ємну кількість секунд, вкажіть нове значення:");
			InputValue = InputScanner.nextInt();
		}

		InputScanner.close();
		
		int hours = InputValue / 3600;
		int minutes = (InputValue % 3600) / 60;
		int seconds = InputValue % 60;
		
		System.out.printf("%d:%02d:%02d\n", hours, minutes, seconds);
	}
}
