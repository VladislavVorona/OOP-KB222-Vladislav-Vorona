import java.util.Scanner;

public class Task07 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		
		System.out.println("Введіть послідовність цілих чисел та 0 для завершення:");
		
		int MaxInt = Integer.MIN_VALUE;
		
		while (true) {
			int Value = InputScanner.nextInt();
			
			if (Value == 0) {
				break;
			}
			
			if (Value > MaxInt) {
				MaxInt = Value;
			}
		}

		InputScanner.close();
		
		System.out.println("Максимальне значення: " + MaxInt);
	}
}
