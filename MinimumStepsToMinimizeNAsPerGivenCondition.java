package dynamic.programming;

import java.util.Scanner;

public class MinimumStepsToMinimizeNAsPerGivenCondition {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/minimum-steps-minimize-n-per-given-condition/
		 * Given a number n, count minimum steps to minimize it to 1 according to the following criteria:
		 * 	If n is divisible by 2 then we may reduce n to n/2.
		 * 	If n is divisible by 3 then you may reduce n to n/3.
		 * 	Decrement n by 1.
		 * 
		 * Input : n = 10
		 * Output : 3
		 * 
		 * Input : 6
		 * Output : 2
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			System.out.println("#" + testCase + ": " + CalcResult(n));
		}
		sc.close();
	}

	private static int CalcResult(int n) {
		int[] dp = new int[n+1];
		if (n < 4) {
			return (n == 1) ? 0 : 1;
		}
		dp[1] = 0;
		dp[2] = dp[3] = 1;
		for (int i=4;i<=n;i++) {
			if (i%2 == 0) {
				dp[i] = (dp[i/2] < dp[i-1]) ? dp[i/2]+1 : dp[i-1]+1;
				if (i%3 == 0) {
				    dp[i] = (dp[i/3]+1 < dp[i]) ? dp[i/3]+1 : dp[i];
				}
			} else if (i%3 == 0) {
				dp[i] = (dp[i/3] < dp[i-1]) ? dp[i/3]+1 : dp[i-1]+1;
			} else {
				dp[i] = dp[i-1] + 1;
			}
		}
		return dp[n];
	}
}
