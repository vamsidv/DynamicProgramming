package dynamic.programming;

import java.util.Scanner;

public class EggDroppingPuzzle {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
		 * The following is a description of the instance of this famous puzzle involving n=2 eggs and a building with k=36 floors.
		 * Suppose that we wish to know which stories in a 36-story building are safe to drop eggs from,
		 * and which will cause the eggs to break on landing. We make a few assumptions:
		 * 		An egg that survives a fall can be used again.
		 * 		A broken egg must be discarded.
		 * 		The effect of a fall is the same for all eggs.
		 * 		If an egg breaks when dropped, then it would break if dropped from a higher floor.
		 * 		If an egg survives a fall then it would survive a shorter fall.
		 * 		It is not ruled out that the first-floor windows break eggs, 
		 * 			nor is it ruled out that the 36th-floor do not cause an egg to break.
		 * If only one egg is available and we wish to be sure of obtaining the right result, the experiment can be carried out in only one way. 
		 * Drop the egg from the first-floor window; if it survives, drop it from the second floor window. 
		 * Continue upward until it breaks. In the worst case, this method may require 36 droppings. 
		 * Suppose 2 eggs are available. What is the least number of egg-droppings that is guaranteed to work in all cases?
		 * The problem is not actually to find the critical floor, 
		 * but merely to decide floors from which eggs should be dropped so that total number of trials are minimized.
		 * 
		 * Input: N=2, K=10
		 * Output: 4
		 * Input: N=3, k=5
		 * Output: 3
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			System.out.println("#" + testCase + ": " + CalcResult(n, k));
		}
		sc.close();
	}

	// Read the approach mentioned in the link: https://www.geeksforgeeks.org/eggs-dropping-puzzle-binomial-coefficient-and-binary-search-solution/
	// It is another way to look at the problem. It also gives better complexity
	private static int CalcResult(int n, int k) {
		int[][] dp = new int [n+1][k+1];
		for (int i=1;i<=n;i++) {
			dp[i][1] = 1;
			dp[i][0] = 0;
		}
		for (int i=1;i<=k;i++) {
			dp[1][i] = i;
		}
		int temp, temp1, a, b;
		for (int i=2;i<=n;i++) {
			for (int j=2;j<=k;j++) {
				temp = Integer.MAX_VALUE;
				for (int x=1;x<j;x++) {
					a = dp[i-1][x-1];
					b = dp[i][j-x];
					temp1 = (a > b) ? a : b;
					temp = (temp < temp1) ? temp : temp1;
				}
				dp[i][j] = temp + 1;
			}
		}
		return dp[n][k];
	}
}
