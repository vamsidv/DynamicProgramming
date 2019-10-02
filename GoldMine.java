package dynamic.programming;

import java.util.Scanner;

public class GoldMine {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/gold-mine-problem/
		 * Given a gold mine of n*m dimensions. 
		 * Each field in this mine contains a positive integer which is the amount of gold in tons. 
		 * Initially the miner is at first column but can be at any row. He can move only (right->,right up /,right down\) that is from a given cell, 
		 * the miner can move to the cell diagonally up towards the right or right or diagonally down towards the right. 
		 * Find out maximum amount of gold he can collect.
		 * Ex: {{1, 3, 3}, {2, 1, 4}, {0, 6, 4}}	Ans: 12
		 * {{1, 3, 1, 5}, {2, 2, 4, 1}, {5, 0, 2, 3}, {0, 6, 1, 2}};	Ans: 16
		 * {{10, 33, 13, 15}, {22, 21, 04, 1}, {5, 0, 2, 3}, {0, 6, 14, 2}};	Ans: 83
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase=1;testCase<=testCases;testCase++) {
			int n,m;
			n = sc.nextInt();
			m = sc.nextInt();
			int data[][] = new int[n][m];
			for (int i=0;i<n;i++) {
				for (int j=0;j<m;j++) {
					data[i][j] = sc.nextInt();
				}
			}
			System.out.println("#" + testCase + ": " + CalcResult(data, n, m));
		}
		sc.close();
	}

	private static long CalcResult(int[][] data, int n, int m) {
		long result = 0;
		if (n == 0 || m == 0) {
			return 0;
		} else if (n == 1) {
		    for (int j=0;j<m;j++) {
		        result = result + data[0][j];
		    }
		    return result;
		} else {
			long dp[][] = new long[n][m];
			for (int i=0;i<n;i++) {
				dp[i][m-1] = data[i][m-1];
			}
			
			for (int j=m-2;j>=0;j--) {
				dp[0][j] = data[0][j] + (dp[0][j+1] > dp[1][j+1] ? dp[0][j+1] : dp[1][j+1]);
				for (int i=1;i<n-1;i++) {
					dp[i][j] = (long)data[i][j] + maxOf(dp[i-1][j+1], dp[i][j+1], dp[i+1][j+1]);
				}
				dp[n-1][j] = data[n-1][j] + (dp[n-1][j+1] > dp[n-2][j+1] ? dp[n-1][j+1] : dp[n-2][j+1]);
			}
			
			for (int i=0;i<n;i++) {
				if (result < dp[i][0]) {
					result = dp[i][0];
				}
			}
		}
		return result;
	}

	private static long maxOf(long l, long m, long n) {
		if (l >= m && l >= n) {
			return l;
		}
		if (m >= l && m >= n) {
			return m;
		}
		return n;
	}
}
