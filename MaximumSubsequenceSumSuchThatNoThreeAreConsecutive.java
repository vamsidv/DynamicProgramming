package dynamic.programming;

import java.util.Scanner;

public class MaximumSubsequenceSumSuchThatNoThreeAreConsecutive {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/maximum-subsequence-sum-such-that-no-three-are-consecutive/
		 * Given a sequence of positive numbers, find the maximum sum that can be formed which has no three consecutive elements present.
		 * Input: arr[] = {1, 2, 3}
		 * Output: 5
		 * We can't take three of them, so answer is: 2 + 3 = 5
		 * 
		 * Input: arr[] = {3000, 2000, 1000, 3, 10}
		 * Output: 5013 
		 * 3000 + 2000 + 3 + 10 = 5013
		 * 
		 * Input: arr[] = {100, 1000, 100, 1000, 1}
		 * Output: 2101
		 * 100 + 1000 + 1000 + 1 = 2101
		 * 
		 * Input: arr[] = {1, 1, 1, 1, 1}
		 * Output: 4
		 * 
		 * Input: arr[] = {1, 2, 3, 4, 5, 6, 7, 8}
		 * Output: 27
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

	private static long CalcResult(int[] data, int n) {
		long dp[][] = new long[3][n];
		long sum = 0;
		if (n < 3) {
			for (int i=0;i<n;i++) {
				sum += data[i];
			}
			return sum;
		}
		dp[0][0] = 0;
		dp[1][0] = dp[2][0] = dp[0][1] = data[0];
		dp[1][1] = data[1];
		dp[2][1] = data[0] + data[1];
		// As we can see that we are always concerned about (i-1)th column while calculating ith column. Therefore we can reduce the space complexity from O(n) to O(1)
		for (int i=2;i<n;i++) {
			dp[0][i] = getMax(dp[0][i-1], dp[1][i-1], dp[2][i-1]);
			dp[1][i] = dp[0][i-1] + data[i];
			dp[2][i] = dp[1][i-1] + data[i];
		}
		return getMax(dp[0][n-1], dp[1][n-1], dp[2][n-1]);
	}
	
	private static long getMax(long a, long b, long c) {
		if ((a >= b) && (a >= c)) {
			return a;
		}
		if ((b >= a) && (b >= c)) {
			return b;
		}
		return c;
	}
}
