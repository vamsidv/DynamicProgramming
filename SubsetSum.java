package dynamic.programming;

import java.util.Scanner;

public class SubsetSum {
	
	private static long dp[][];

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
		 * https://www.geeksforgeeks.org/subset-sum-problem-osum-space/
		 * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
		 * Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
		 * Output:  True  //There is a subset (4, 5) with sum 9.
		 * Input: set[] = {1,5,11,5}, sum = 11
		 * Output: True
		 * Input: set[] = {1,3,5}, sum = 2
		 * Output: False
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int s = sc.nextInt();
			int data[] = new int[s];
			for (int i=0;i<s;i++) {
				data[i] = sc.nextInt();
			}
			int n = sc.nextInt();
			dp = new long[n+1][s];
			for (int i=0;i<=n;i++) {
				for (int j=0;j<s;j++) {
					dp[i][j] = -1;
				}
			}
			
			System.out.println("#" + testCase + ": " + (CalcResult(data, s, n, 0)==1));
			System.out.println("#" + testCase + ": " + (CalcResultBP(data, s, n, 0)==1));
			System.out.println("#" + testCase + ": " + (CalcResultBPOn(data, s, n, 0)==1));
		}
		sc.close();
	}

	private static long CalcResult(int[] data, int s, int n, int index) {
		if (n < 0) {
			return 0;
		}
		if (dp[n][index] != -1) {
			return dp[n][index];
		}
		if (n == 0) {
			dp[n][index] = 1;
			return dp[n][index];
		}
		if (index == s-1) {
			dp[n][index] = (data[index] == n) ? 1 : 0;
			return dp[n][index];
		}
		dp[n][index] = ((CalcResult(data, s, n-data[index], index+1) == 1) || (CalcResult(data, s, n, index+1) == 1)) ? 1 : 0;
		return dp[n][index];
	}
	
	private static long CalcResultBP(int[] data, int s, int n, int index) {
		for (int i=0;i<s;i++) {
			dp[0][i] = 1;
		}
		for (int i=1;i<=n;i++) {
			dp[i][0] = 0;
		}
		dp[data[0]][0] = 1;
		for (int j=1;j<s;j++) {
			for (int i=1;i<=n;i++) {
				dp[i][j] = ((dp[i][j-1] == 1) || (i-data[j] >= 0 && dp[i-data[j]][j-1] == 1)) ? 1 : 0;
			}
		}
		return dp[n][s-1];
	}
	
	private static long CalcResultBPOn(int[] data, int s, int n, int index) {
		long a[] = new long[n+1];
		a[0] = 1;
		for (int i=1;i<=n;i++) {
			a[i] = 0;
		}
		a[data[0]] = 1;
		for (int j=1;j<s;j++) {
			// It is important to understand why we are looping in backward direction
			for (int i=n;i>0;i--) {
				a[i] = ((a[i] == 1) || (i-data[j] >= 0 && a[i-data[j]] == 1)) ? 1 : 0;
			}
		}
		return a[n];
	}
}
