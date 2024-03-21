public class Task10 {
	public static void main(String[] args) {
		int[] arr = {5, 1, 2, 8, 43, 55, 12, 4};
		int result = max(arr);
		System.out.println("Максимальне значення в масиві: " + result);
	}
	
	public static int max(int[] arr) {
		int MaxNum = arr[0];

		for (int xx = 1; xx < arr.length; xx++) {
			if (arr[xx] > MaxNum) {
				MaxNum = arr[xx];
			}
		}

		return MaxNum;
	}
}
