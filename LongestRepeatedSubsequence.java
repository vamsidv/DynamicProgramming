package dynamic.programming;

import java.util.Scanner;

public class LongestRepeatedSubsequence {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/longest-repeated-subsequence/
		 * Given a string, print the longest repeating subsequence such that the two subsequence don’t have same string character at same position,
		 * i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string.
		 * Input: str = "aabb"
		 * Output: "ab"
		 * Input: str = "aab"
		 * Output: "a"
		 * The two subsequence are 'a'(first) and 'a' (second). 
		 * Note that 'b' cannot be considered as part of subsequence as it would be at same index in both.
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int l1 = sc.nextInt();
			sc.nextLine();
			String s1 = sc.nextLine();
			System.out.println("#" + testCase + ": " + CalcResult(s1, s1, l1, l1));
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
				if ((s1.charAt(i) == s2.charAt(j)) && (i != j)) {
					dp[i][j] = dp[i+1][j+1] + 1;
				} else {
					dp[i][j] = (dp[i+1][j] > dp[i][j+1]) ? dp[i+1][j] : dp[i][j+1];
				}
			}
		}
		return dp[0][0];
	}
}
