import java.util.Scanner;

public class Task03 {
	private static final int pass = 55555;

	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		
		System.out.println("Введіть пароль:");
		int InputPass = InputScanner.nextInt();

		InputScanner.close();
		
		if (InputPass == pass) {
			System.out.println("Hello, Agent");
		} else {
			System.out.println("Access denied");
		}
	}
}
