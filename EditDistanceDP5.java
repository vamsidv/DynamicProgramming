package dynamic.programming;

import java.util.Scanner;

public class EditDistanceDP5 {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/edit-distance-dp-5/
		 * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
		 * 	Insert
		 * 	Remove
		 * 	Replace
		 * All of the above operations are of equal cost.
		 * Input:   str1 = "geek", str2 = "gesek"
		 * Output:  1
		 * We can convert str1 into str2 by inserting a 's'.
		 * 
		 * Input:   str1 = "cat", str2 = "cut"
		 * Output:  1
		 * We can convert str1 into str2 by replacing 'a' with 'u'.
		 * 
		 * Input:   str1 = "sunday", str2 = "saturday"
		 * Output:  3
		 * Last three and first characters are same.  We basically need to convert "un" to "atur".
		 * This can be done using below three operations. 
		 * Replace 'n' with 'r', insert t, insert a
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		sc.nextLine();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int l1 = sc.nextInt();
			int l2 = sc.nextInt();
			sc.nextLine();
			String temp = sc.nextLine();
			String s1 = temp.split(" ")[0];
			String s2 = temp.split(" ")[1];
			System.out.println("#" + testCase + ": " + CalcResult(s1, s2, l1, l2));
		}
		sc.close();
	}

	private static int CalcResult(String s1, String s2, int l1, int l2) {
		int[][] dp = new int[l1+1][l2+1];
		for (int i=0;i<=l2;i++) {
			dp[0][i] = i;
		}
		for (int i=0;i<=l1;i++) {
			dp[i][0] = i;
		}
		for (int i=1;i<=l1;i++) {
			for (int j=1;j<=l2;j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					// findMin(replace, remove, insert)
					dp[i][j] = findMin(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
				}
			}
		}
		return dp[l1][l2];
	}

	private static int findMin(int i, int j, int k) {
		if ((i <= j) && (i <= k)) {
			return i;
		}
		if ((j <= i) && (j <= k)) {
			return j;
		}
		return k;
	}
}
