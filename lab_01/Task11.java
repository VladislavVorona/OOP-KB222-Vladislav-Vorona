public class Task11 {
	public static void main(String[] args) {
		int[] arr = {5, 1, 2, 8, 43, 55, 12, 4};
		int result = Sum(arr);
		System.out.println("Сума парних чисел в масиві: " + result);
	}
	
	public static int Sum(int[] arr) {
		int NumSum = 0;
		for (int xx : arr) {
			if (xx % 2 == 0) {
				NumSum += xx;
			}
		}
		return NumSum;
	}
}
