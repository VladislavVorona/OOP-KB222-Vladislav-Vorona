import java.util.Scanner;

public class Task04 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		int InputValue = 0;

		while (InputValue <= 0) {
			System.out.println("Вкажіть кількість незнайомців для зустрічі:");
			InputValue = InputScanner.nextInt();

			if (InputValue <= 0) {
				System.out.println("Кількість незнайомців має бути додатнім значенням, введіть нове значення:");
			}
		}

		InputScanner.nextLine();

		System.out.println("Введіть імена незнайомців");

		for (int xx = 1; xx <= InputValue; xx++) {
			System.out.println("Ім'я незнайомця під номером " + (xx) + ":");
			String StrangerName = InputScanner.nextLine();
			System.out.println("Hello, " + StrangerName);
		}
		
		InputScanner.close();
	}
}
