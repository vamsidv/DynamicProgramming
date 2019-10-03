package dynamic.programming;

import java.util.Scanner;

public class CuttingARod {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
		 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
		 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
		 * For example, if length of the rod is 8 and the values of different pieces are given as following,
		 * 		length   | 1   2   3   4   5   6   7   8
		 * 		price    | 1   5   8   9  10  17  17  20
		 * then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
		 * 		length   | 1   2   3   4   5   6   7   8
		 * 		price    | 3   5   8   9  10  17  17  20
		 * then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases ; testCase++) {
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
		long[] dp = new long[n+1];
		dp[0] = 0;
		long max;
		for (int i=1;i<=n;i++) {
			max = 0;
			for (int j=0;j<i;j++) {
				max = (max > dp[j]+data[i-j-1]) ? max : dp[j]+data[i-j-1];
			}
			dp[i] = max;
		}
		return dp[n];
	}
}
