import java.util.Scanner;

public class Task01 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);

		System.out.println("Вкажіть ваше ім'я:");
		String text = InputScanner.nextLine();

		InputScanner.close();

		System.out.println("Hello, " + text);
	}
}
