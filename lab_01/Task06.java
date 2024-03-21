import java.util.Scanner;

public class Task06 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		
		System.out.println("Вкажіть загальну суму рахунку та кількість друзів:");
		double bill = InputScanner.nextDouble();
		int friends = InputScanner.nextInt();

		InputScanner.close();
		
		if (bill <= 0) {
			System.out.println("Загальна сума рахунку має бути більше 0.");
			return;
		}
		
		if (friends <= 0) {
			System.out.println("Кількість друзів має бути більше 0.");
			return;
		}
		
		double result = bill * 1.1 / friends;
		
		System.out.printf("Сума сплати на одного друга: %.2f\n", result);
	}
}
