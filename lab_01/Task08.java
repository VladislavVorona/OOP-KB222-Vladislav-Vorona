import java.util.Scanner;

public class Task08 {
	public static void main(String[] args) {
		Scanner InputScanner = new Scanner(System.in);
		
		System.out.println("Введіть послідовність цілих чисел та 0 для завершення:");
		
		int NumbSum = 0;
		int NumbCount = 0;
		
		while (true) {
			int value = InputScanner.nextInt();
			
			if (value == 0) break;
			
			NumbSum += value;
			NumbCount++;
		}

		InputScanner.close();
		
		System.out.println("Середнє значення: " + ((double) NumbSum / NumbCount));
	}
}
