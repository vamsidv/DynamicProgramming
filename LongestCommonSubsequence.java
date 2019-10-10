package dynamic.programming;

import java.util.Scanner;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
		 * Given two sequences, find the length of longest subsequence present in both of them.
		 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
		 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
		 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
		 * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int l1 = sc.nextInt();
			int l2 = sc.nextInt();
			sc.nextLine();
			String s1 = sc.nextLine();
			String s2 = sc.nextLine();
			System.out.println("#" + testCase + ": " + CalcResult(s1, s2, l1, l2));
		}
		sc.close();
	}

	private static int CalcResult(String s1, String s2, int l1, int l2) {
		int[][] dp = new int[l1+1][l2+1];
		for (int i=0;i<=l1;i++) {
			dp[i][l2] = 0;
		}
		for (int i=0;i<=l2;i++) {
			dp[l1][i] = 0;
		}
		for (int i=l1-1;i>=0;i--) {
			for (int j=l2-1;j>=0;j--) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i][j] = dp[i+1][j+1] + 1;
				} else {
					dp[i][j] = (dp[i+1][j] > dp[i][j+1]) ? dp[i+1][j] : dp[i][j+1];
				}
			}
		}
		return dp[0][0];
	}
}
