package dynamic.programming;

import java.util.Scanner;

public class SequencesGivenLengthEveryElementEqualTwicePrevious {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/sequences-given-length-every-element-equal-twice-previous/
		 * Given two integers m & n, 
		 * find the number of possible sequences of length n such that each of the next element is greater than or equal to twice of the previous element but less than or equal to m.
		 * Input : m = 10, n = 4
		 * Output : 4
		 * There should be n elements and value of last element should be at-most m.
		 * The sequences are {1, 2, 4, 8}, {1, 2, 4, 9}, {1, 2, 4, 10}, {1, 2, 5, 10}
		 * 
		 * Input : m = 5, n = 2
		 * Output : 6
		 * The sequences are {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 4}, {2, 5}
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			System.out.println("#" + testCase + ": " + CalcResult(m,n));
		}
		sc.close();
	}

	private static long CalcResult(int m, int n) {
		long[][] dp = new long[m+1][n];
		for (int i=1;i<=m;i++) {
			dp[i][0] = 1;
		}
		for (int j=1;j<n;j++) {
			for (int i=1;i<=m;i++) {
				dp[i][j] = 0;
				for (int k=(i/2);k>0;k--) {
					dp[i][j] += dp[k][j-1];
				}
			}
		}
		long result = 0;
		for (int i=1;i<=m;i++) {
			result += dp[i][n-1];
		}
		return result;
	}
}
