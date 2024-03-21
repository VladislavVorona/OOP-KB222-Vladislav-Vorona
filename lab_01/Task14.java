import java.util.Arrays;

public class Task14 {
	public static void main(String[] args) {
		int[] arr = {5, 1, 4, 2, 7};
		System.out.println("Початковий масив 1: " + Arrays.toString(arr));
		cycleSwap(arr);
		System.out.println("Масив після зсуву: " + Arrays.toString(arr));

		int shift = 7;
		cycleSwap(arr, shift);
		System.out.println("Масив після зсуву на " + shift + " позицій: " + Arrays.toString(arr));
	}
	
	public static void cycleSwap(int[] arr) {
		if (arr.length <= 1) {
			return;
		}
		
		int Box = arr[arr.length - 1];
		for (int xx = arr.length - 1; xx > 0; xx--) {
			arr[xx] = arr[xx - 1];
		}
		arr[0] = Box;
	}
	
	public static void cycleSwap(int[] arr, int shift) {
		for (int xx = 0; xx < shift; xx++) {
			cycleSwap(arr);
		}
	}
}
