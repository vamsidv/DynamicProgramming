package dynamic.programming;

import java.util.Scanner;

public class PaintingFence {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/painting-fence-algorithm/
		 * Given a fence with n posts and k colors, find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color.
		 * Since answer can be large return it modulo 10^9 + 7.
		 * Input : n = 2 k = 4
		 * Output : 16
		 * Input : n = 3 k = 2
		 * Output : 6
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			/**
			 * Depending on the question assumption, the approach can be chosen
			 * for n=9 and k=2(0/1) consider the following 2 stated
			 * State 1: 1 1 0 1 0 0 1 1 0
			 * State 2: 1 0 1 0 1 1 0 1 0
			 * State 1 means that 2 adjacent posts with same color can appear any number of times. 
			 * The number of adjacent posts with same color is atmost 2 which means 3 adjacent posts with same color is not allowed.
			 * If this is the expectation, CalcResult1(n,k) can be used
			 * If State 1 is invalid but state 2 is valid then, CalcResult(n,k) can be used.
			 */
			/**
			 * Both the approaches can be solved with O(1) space as for every iteration, we only need values of previous iteration.
			 */
			System.out.println("#" + testCase + ": " + CalcResult(n,k));
			System.out.println("#" + testCase + ": " + CalcResult1(n,k));
		}
		sc.close();
	}

	private static long CalcResult(int n, int k) {
		long[][] dp = new long[n+1][2];
		/**
		 * dp[i][0] means number of possibilities for n=i with no 2 adjacent posts with same color
		 * dp[i][1] means number of possibilities for n=i containing exactly one set of adjacent posts with same color
		 * dp[i][0] = dp[i-1][0] * (k-1)
		 * dp[i][1] = dp[i-1][0] + (dp[i-1][1] * (k-1))
		 */
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return k;
		}
		dp[2][0] = k*(k-1);
		dp[2][1] = k;
		for (int i=3;i<=n;i++) {
			dp[i][0] = (dp[i-1][0] * (k-1)) % 1000000007;
			dp[i][1] = (dp[i-1][0] + (dp[i-1][1] * (k-1)) % 1000000007) % 1000000007;
		}
		return (dp[n][0] + dp[n][1]) % 1000000007;
	}
	
	private static long CalcResult1(int n, int k) {
		long[][] dp = new long[n+1][2];
		/**
		 * dp[i][0] means number of possibilities for n=i with ith and (i-1)th fence not same color
		 * dp[i][1] means number of possibilities for n=i with ith and (i-1)th fence being same color
		 * dp[i][0] = (dp[i-1][0] + dp[i-1][1]) * (k-1)
		 * dp[i][1] = dp[i-1][0]
		 */
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return k;
		}
		dp[2][0] = k*(k-1);
		dp[2][1] = k;
		for (int i=3;i<=n;i++) {
			dp[i][0] = (((dp[i - 1][0] + dp[i - 1][1]) % 1000000007) * (k - 1)) % 1000000007;
			dp[i][1] = dp[i-1][0];
		}
		return (dp[n][0] + dp[n][1]) % 1000000007;
	}
}
