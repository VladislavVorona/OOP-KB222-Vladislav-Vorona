public class Task12 {
	public static void main(String[] args) {
		int[] arr = {5, 5, 10, 4, 14, 2, 1, 3};
		boolean[] result = getSumCheckArray(arr);
		System.out.println("Результат: " + java.util.Arrays.toString(result));
	}
	
	public static boolean[] getSumCheckArray(int[] arr) {
		boolean[] result = new boolean[arr.length];
		
		for (int xx = 2; xx < arr.length; xx++) {
			result[xx] = (arr[xx] == arr[xx - 1] + arr[xx - 2]);
		}
		
		return result;
	}
}
