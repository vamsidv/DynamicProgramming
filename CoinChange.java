package dynamic.programming;

import java.util.Scanner;

public class CoinChange {
	
	private static long dp[][];

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/coin-change-dp-7/
		 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, 
		 * how many ways can we make the change? The order of coins doesn’t matter.
		 * For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. 
		 * For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		
		for (int testCase=1;testCase<=testCases;testCase++) {
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
			System.out.println("#" + testCase + ": " + calcResult(0,n,s,data));
			System.out.println("#" + testCase + ": " + calcResultBP(0,n,s,data));
			System.out.println("#" + testCase + ": " + calcResultBPWithOnSpace(0,n,s,data));
		}
		sc.close();
	}

	private static long calcResultBPWithOnSpace(int i, int n, int s,
			int[] data) {
		long dp[] = new long[n+1];
		dp[0] = 1;
		for (int j=0;j<s;j++) {
			for (int index=data[j]; index <= n ;index++) {
				dp[index] = dp[index] + dp[index - data[j]];
			}
		}
		return dp[n];
	}

	private static long calcResultBP(int index, int n, int s, int[] data) {
		for(int i=0;i<=n;i++) {
			for (int j=0;j<s;j++) {
				dp[i][j] = 0;
			}
		}
		for (int j=0;j<s;j++) {
			dp[0][j] = 1;
		}
		long x,y;
		for (int i=1;i<=n;i++) {
			for (int j=0;j<s;j++) {
				x = (i-data[j] >= 0) ? dp[i-data[j]][j] : 0;
				y = (j>0) ? dp[i][j-1] : 0;
				dp[i][j] = x+y;
			}
		}
		return dp[n][s-1];
	}

	// Top Down DP Approach
	private static long calcResult(int i, int n, int s, int[] data) {
		if (dp[n][i] != -1) {
			return dp[n][i];
		}
		if (i == s-1) {
			if (n%data[i] == 0) {
				dp[n][i] = 1;
				return 1;
			} else {
				dp[n][i] = 0;
				return 0;
			}
		}
		long noWays = 0;
		for (int it=0;data[i]*it<=n;it++) {
			if (calcResult(i+1, n-(data[i]*it), s, data) != 0) {
				noWays += calcResult(i+1,  n-(data[i]*it), s, data);
			}
		}
		dp[n][i] = noWays;
		return dp[n][i];
	}
}
