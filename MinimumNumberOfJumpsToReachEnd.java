package dynamic.programming;

import java.util.Scanner;

public class MinimumNumberOfJumpsToReachEnd {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
		 * Given an array of integers where each element represents the max number of steps that can be made forward from that element.
		 * Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
		 * If an element is 0, then cannot move through that element. Print -1 if it is not possible to reach the end
		 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
		 * Output: 3 (1-> 3 -> 8 ->9)
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int[] data = new int[n];
			for (int i=0;i<n;i++) {
				data[i] = sc.nextInt();
			}
			System.out.println("#" + testCase + ": " + CalcResult(data, n));
		}
		sc.close();
	}

	private static int CalcResult(int[] data, int n) {
		int[] dp = new int[n];
		dp[n-1] = 0;
		int min;
		for (int i = n-2; i >= 0; i--) {
			if (data[i] == 0) {
				dp[i] = -1;
			} else {
				min = -1;
				for (int j=i+1; j<=i+data[i] && j < n; j++) {
					if (dp[j] != -1) {
						min = (min == -1) ? dp[j] + 1 : ((min > dp[j] + 1) ? dp[j]+1 : min);
					}
				}
				dp[i] = min;
			}
		}
		return dp[0];
	}
}
