package dynamic.programming;

import java.util.Scanner;

public class UnboundedKnapsack {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
		 * Given a knapsack weight W and a set of n items with certain value val[i] and weight wt[i],
		 * we need to calculate minimum amount that could make up this quantity exactly.
		 * This is different from classical Knapsack problem,
		 * here we are allowed to use unlimited number of instances of an item.
		 * 
		 * Input : W = 100
		 * 		   val[]  = {1, 30}
		 * 		   wt[] = {1, 50}
		 * Output : 100
		 * 
		 * Input : W = 8
		 * 		   val[] = {10, 40, 50, 70}
		 * 		   wt[]  = {1, 3, 4, 5}
		 * Output : 110 
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
		long[] dp = new long[w+1];
		dp[0] = 0;
		long max, temp;
		for (int i=1;i<=w;i++) {
			max = 0;
			for (int j=0;j<n;j++) {
				if (weights[j] <= i) {
					temp = dp[i-weights[j]] + values[j];
					max = (max > temp) ? max : temp;
				}
			}
			dp[i] = max;
		}
		return dp[w];
	}
}
