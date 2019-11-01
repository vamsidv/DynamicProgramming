package dynamic.programming;

import java.util.Scanner;

public class Knapsack01 {
	
	private static long dp[][];

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
		 * Given weights and values of n items, 
		 * put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
		 * In other words, given two integer arrays val[0..n-1] and wt[0..n-1] 
		 * which represent values and weights associated with n items respectively.
		 * Also given an integer W which represents knapsack capacity,
		 * find out the maximum value subset of val[] such that 
		 * sum of the weights of this subset is smaller than or equal to W.
		 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
		 * 
		 * Value[] = {60,100,120}
		 * Weight[] = {10,20,30}
		 * W = 50
		 * Output = 220
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int w = sc.nextInt();
			int[] values = new int[n];
			int[] weights = new int[n];
			for (int i=0;i<n;i++) {
				values[i] = sc.nextInt();
			}
			for (int i=0;i<n;i++) {
				weights[i] = sc.nextInt();
			}
			
			System.out.println("#" + testCase + ": " + CalcResult(values, weights, n, w));
		}
		sc.close();
	}

	private static long CalcResult(int[] values, int[] weights, int n, int w) {
		dp = new long[n+1][w+1];
		// f(n,w) = max[ (val[n] + f(n-1, w-wei[n])), f(n-1,w) ]
		for (int i=0;i<=n;i++) {
			dp[i][0] = 0;
		}
		for (int i=0;i<=w;i++) {
			dp[0][i] = 0;
		}
		long a, b;
		for (int j=1;j<=w;j++) {
			for (int i=1;i<=n;i++) {
				a = (weights[i-1] <= j) ? (values[i-1] + dp[i-1][j-weights[i-1]]) : 0;
				b = dp[i-1][j];
				dp[i][j] = (a > b) ? a : b;
			}
		}
		return dp[n][w];
	}
}
