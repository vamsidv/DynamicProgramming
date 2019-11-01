package dynamic.programming;

import java.util.Scanner;

public class DiceThrow {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/dice-throw-dp-30/
		 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. 
		 * X is the summation of values on each face when all the dice are thrown.
		 * 
		 * Input: M=6, N=3, X=12
		 * Output: 25
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			int x = sc.nextInt();
			System.out.println("#" + testCase + ": " + CalcResult(m, n, x));
		}
		sc.close();
	}

	private static long CalcResult(int m, int n, int x) {
		long[][] dp = new long[x][n];
		/**
		 * dp[i][j] = number of ways of getting i by using j throws
		 * dp[i][j] = sum (dp[i-k][j-1]) for k = 1 to m
		 */
		int i;
		for (i=0;i<m && i<x;i++) {
			dp[i][0] = 1;
		}
		for (;i<x;i++) {
			dp[i][0] = 0;
		}
		for (i=1;i<n;i++) {
			dp[0][i] = 0;
		}
		long sum;
		for (int j=1;j<n;j++) {
			for (i=1;i<x;i++) {
				sum = 0;
				// NOTE: This for loop can be further removed by storing previous result.
				// It can be maintained as a sliding window. Therefore the loop of complexity O(m) can be O(1) now.
				for (int k=1; i-k >= 0 && k <= m; k++) {
					sum = sum + dp[i-k][j-1];
				}
				dp[i][j] = sum;
			}
		}
		return dp[x-1][n-1];
	}
}
