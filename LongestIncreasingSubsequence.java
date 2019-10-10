package dynamic.programming;

import java.util.Scanner;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
		 * Find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order.
		 * For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
		 * Input  : arr[] = {3, 10, 2, 1, 20}
		 * Output : Length of LIS = 3
		 * The longest increasing subsequence is 3, 10, 20
		 * Input  : arr[] = {3, 2}
		 * Output : Length of LIS = 1
		 * The longest increasing subsequences are {3} and {2}
		 * Input : arr[] = {50, 3, 10, 7, 40, 80}
		 * Output : Length of LIS = 4
		 * The longest increasing subsequence is {3, 7, 40, 80}
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int[] data = new int[n];
			for (int i=0;i<n;i++) {
				data[i] = sc.nextInt();
			}
			System.out.println("#" + testCase + ": " + CalcResult(data, n));
		}
		sc.close();
	}

	private static int CalcResult(int[] data, int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		int value, max;
		max = 1;
		for (int i=1;i<n;i++) {
			value = 1;
			for (int j=i-1;j>=0;j--) {
				if (data[i] > data[j]) {
					value = (value > dp[j] + 1) ? value : dp[j] + 1;
				}
			}
			max = (max > value) ? max : value;
			dp[i] = value;
		}
		return max;
	}
}
