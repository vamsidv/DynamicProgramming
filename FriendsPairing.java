package dynamic.programming;

import java.util.Scanner;

public class FriendsPairing {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/friends-pairing-problem/
		 * Given n friends, each one can remain single or can be paired up with some other friend. 
		 * Each friend can be paired only once. Find out the total number of ways in which friends can remain single or can be paired up.
		 * Input  : n = 3
		 * Output : 4
		 * Explanation
		 * {1}, {2}, {3} : all single
		 * {1}, {2, 3} : 2 and 3 paired but 1 is single.
		 * {1, 2}, {3} : 1 and 2 are paired but 3 is single.
		 * {1, 3}, {2} : 1 and 3 are paired but 2 is single.
		 * Note that {1, 2} and {2, 1} are considered same.
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		
		for (int testCase = 1; testCase <= testCases ; testCase++) {
			int n = sc.nextInt();
			System.out.println("#" + testCase + ": " + CalcResult(n));
			System.out.println("#" + testCase + ": " + CalcResultO1Soln(n));
		}
		sc.close();
	}

	private static long CalcResultO1Soln(int n) {
		if (n==1) {
			return 1;
		}
		if (n==2) {
			return 2;
		}
		long a = 1;
		long b = 2;
		long c = b + (2)*a;
		for (int i=3;i<n;i++) {
			a = b;
			b = c;
			c = b + (i)*a;
		}
		return c;
	}

	private static long CalcResult(int n) {
		/**
		 * f(n) = ways n people can remain single or pair up
		 * For n-th person there are two choices:
		 * 		1) n-th person remains single, we recur for f(n-1)
		 * 		2) n-th person pairs up with any of the remaining n-1 persons. We get (n - 1) * f(n - 2)
		 * Therefore we can recursively write f(n) as:
		 * f(n) = f(n - 1) + (n - 1) * f(n - 2)
		 */
		if (n==1) {
			return 1;
		}
		if (n==2) {
			return 2;
		}
		long dp[] = new long[n];
		dp[0] = 1;
		dp[1] = 2;
		for (int i=2;i<n;i++) {
			dp[i] = dp[i-1] + ((i) * dp[i-2]);
		}
		return dp[n-1];
	}
}
