package dynamic.programming;

import java.util.Scanner;

public class MaximumLengthSnakeSequence {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/find-maximum-length-snake-sequence/
		 * Given a grid of numbers, find maximum length Snake sequence.
		 * A snake sequence is made up of adjacent numbers in the grid such that for each number, the number on the right or the number below it is +1 or -1 its value.
		 * For example, if you are at location (x, y) in the grid, you can either move right i.e. (x, y+1) if that number is ± 1 or move down i.e. (x+1, y) if that number is ± 1.
		 * 9, 6, 5, 2
		 * 8, 7, 6, 5
		 * 7, 3, 1, 6
		 * 1, 1, 1, 7
		 * Output: 7 (9, 8, 7, 6, 5, 6, 7)
		 * 9
		 * 8, 7, 6, 5
		 *          6
		 *          7
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			int[][] data = new int[m][n];
			for (int i=0;i<m;i++) {
				for (int j=0;j<n;j++) {
					data[i][j] = sc.nextInt();
				}
			}
			System.out.println("#" + testCase + ": " + CalcResult(data, m, n));
		}
		sc.close();
	}

	private static long CalcResult(int[][] data, int m, int n) {
		int[][] dp = new int[m][n];
		dp[m-1][n-1] = 1;
		for (int j=n-2;j>=0;j--) {
			if ((data[m-1][j] == data[m-1][j+1]+1) || (data[m-1][j] == data[m-1][j+1]-1)) {
				dp[m-1][j] = dp[m-1][j+1] + 1;
			} else {
				dp[m-1][j] = 1;
			}
		}
		for (int i=m-2;i>=0;i--) {
			if ((data[i][n-1] == data[i+1][n-1] + 1) || (data[i][n-1] == data[i+1][n-1] - 1)) {
				dp[i][n-1] = dp[i+1][n-1] + 1;
			} else {
				dp[i][n-1] = 1;
			}
			for (int j=n-2;j>=0;j--) {
				int x=0,y=0;
				if ((data[i][j] == data[i+1][j] + 1) || (data[i][j] == data[i+1][j] - 1)) {
					x = dp[i+1][j] + 1;
				}
				if ((data[i][j] == data[i][j+1] + 1) || (data[i][j] == data[i][j+1] - 1)) {
					y = dp[i][j+1] + 1;
				}
				x = (x > y) ? x : y;
				dp[i][j] = (x > 0) ? x : 1;
			}
		}
		int max = 0;
		for (int i=0;i<m;i++) {
			for (int j=0;j<n;j++) {
				max = (dp[i][j] > max) ? dp[i][j] : max;
			}
		}
		return max;
	}
}
