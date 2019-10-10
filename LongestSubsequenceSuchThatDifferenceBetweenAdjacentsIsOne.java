package dynamic.programming;

import java.util.Scanner;

public class LongestSubsequenceSuchThatDifferenceBetweenAdjacentsIsOne {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/longest-subsequence-such-that-difference-between-adjacents-is-one/
		 * Given an array of n size, the task is to find the longest subsequence such that difference between adjacents is one.
		 * Input :  arr[] = {10, 9, 4, 5, 4, 8, 6}
		 * Output :  3
		 * As longest subsequences with difference 1 are, {10, 9, 8}, {4, 5, 4} and {4, 5, 6}
		 * 
		 * Input :  arr[] = {1, 2, 3, 2, 3, 7, 2, 1}
		 * Output :  7
		 * As longest consecutive sequence is {1, 2, 3, 2, 3, 2, 1}
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
		int max;
		for (int i=1;i<n;i++) {
			max = 1;
			for (int j=i-1;j>=0;j--) {
				if ((data[i] == data[j] + 1) || (data[i] == data[j] - 1)) {
					max = (max > (dp[j]+1)) ? max : (dp[j]+1);
				}
			}
			dp[i] = max;
		}
		max = 0;
		for (int i=0;i<n;i++) {
			max = (max > dp[i]) ? max : dp[i];
		}
		return max;
	}
}
