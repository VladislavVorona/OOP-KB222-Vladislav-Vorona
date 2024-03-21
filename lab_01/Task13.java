import java.util.Arrays;

public class Task13 {
	public static void main(String[] args) {
		int[] arr = {5, 2, 13, 8, 3, 4, 12, 2};
		int[] result = removeLocalMaxima(arr);
		System.out.println("Копія масиву: " + Arrays.toString(result));
	}
	
	public static int[] removeLocalMaxima(int[] arr) {
		int count = 0;
		
		for (int xx = 1; xx < arr.length - 1; xx++) {
			if (arr[xx] > arr[xx - 1] && arr[xx] > arr[xx + 1]) {
				count++;
			}
		}
		
		int[] result = new int[arr.length - count];
		int ind = 0;
		for (int xx = 0; xx < arr.length; xx++) {
			if (xx == 0 || xx == arr.length - 1 || !(arr[xx] > arr[xx - 1] && arr[xx] > arr[xx + 1])) {
				result[ind++] = arr[xx];
			}
		}
		
		return result;
	}
}
