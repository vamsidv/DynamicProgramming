package dynamic.programming;

import java.util.Scanner;

public class TilingProblem {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/tiling-problem/
		 * Given a “2 x n” board and tiles of size “2 x 1”, 
		 * count the number of ways to tile the given board using the 2 x 1 tiles. 
		 * A tile can either be placed horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		int n;
		for (int testCase = 1; testCase <= testCases; testCase++) {
			n = sc.nextInt();
			System.out.println("#" + testCase + ": " + calcResult(n));
			System.out.println("#" + testCase + ": " + calcResultO1(n));
		}
		sc.close();
	}

	private static long calcResultO1(int n) {
		if ((n >= 0) && (n <= 2)) {
			return n;
		}
		long a = 0;
		long b = 1;
		long c = 2;
		for (int i=3;i<=n;i++) {
			a = b;
			b = c;
			c = a + b;
		}
		return c;
	}

	private static long calcResult(int n) {
		long dp[] = new long[n+1];
		if ((n >= 0) && (n <= 2)) {
			return n;
		}
		dp[1] = 1;
		dp[2] = 2;
		for (int i=3;i<=n;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n];
	}
}
