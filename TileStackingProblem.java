package dynamic.programming;

import java.util.Scanner;

public class TileStackingProblem {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/tile-stacking-problem/
		 * https://iq.opengenus.org/tile-stacking-problem/
		 * A stable tower of height n is a tower consisting of exactly n tiles of unit height
		 * which are stacked vertically in such a way, that no bigger tile is placed on a smaller tile.
		 * We have infinite number of tiles of sizes 1, 2, …, m. 
		 * The task is calculate the number of different stable tower of height n that can be built from these tiles, 
		 * with a restriction that you can use at most k tiles of each size in the tower.
		 * Note: Two tower of height n are different if and only if there exists a height h (1 <= h <= n), 
		 * such that the towers have tiles of different sizes at height h.
		 * 
		 * Input : n = 3, m = 3, k = 1.
		 * Output : 1
		 * Possible sequences: { 1, 2, 3}. 
		 * Hence answer is 1.
		 * 
		 * Input : n = 3, m = 3, k = 2.
		 * Output : 7
		 * {1, 1, 2}, {1, 1, 3}, {1, 2, 2}, {1, 2, 3}, {1, 3, 3}, {2, 2, 3}, {2, 3, 3}.
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			System.out.println("#" + testCase + ": " + CalcResult(n, m, k));
		}
		sc.close();
	}

	/**
	 * Let us try to simplify the problem be removing few conditions and then generalizing it to actual problem
	 */
	private static long CalcResult(int n, int m, int k) {
		/**
		 * Let us try a very simple variation of the problem.
		 * Consider the original problem removing the restriction of repetition upto k times.
		 * Instead of repetition upto k times, let us try with no repetitions. Also (m >= n)
		 * Now the problem is just choosing n elements from m elements (mCn).
		 * But let us try the problem with dp so that we can generalize the approach to original problem.
		 * dp[i][j] = No. of sequences with bottom tower size = i and height = j
		 * Therefore dp[i][j] = sum (dp[i-x][j-1]) where x=1 to i
		 * dp[0][i] = 0
		 * dp[i][0] = 1
		 * dp[0][0] = 1
		 * Now the current answer will be same as m choose n (mCn)
		 */
		long[][] dp1 = new long[m][n];
		long counter, result1;
		for (int i=0;i<m;i++) {
			dp1[i][0] = 1;
		}
		for (int i=1;i<n;i++) {
			dp1[0][i] = 0;
		}
		for (int i=1;i<m;i++) {
			for (int j=1;j<n;j++) {
				counter = 0;
				for (int x=0;x<i;x++) {
					counter = counter + dp1[x][j-1];
				}
				dp1[i][j] = counter;
			}
		}
		result1 = 0;
		for (int i=0;i<m;i++) {
			result1 = result1 + dp1[i][n-1];
		}
		
		/**
		 * Now lets add one constraint to the above problem.
		 * We can repeat the tiles infinite number of times (instead of k in the actual question)
		 * How can we modify the previous solution to match our current usecase.
		 * If we look at the previous solutions relation
		 * 		dp[i][j] = sum (dp[i-x][j-1]) where x=1 to i
		 * We can observe that to we are considering the previous columns values. 
		 * This is because we were not allowing any repetitions.
		 * If we allow 2 repetitions, we would consider 2 columns. Extending it to any number of repetitions.
		 * dp[i][j] = sum(sum(dp[i-x][j-y])) where x = 1 to i and y = 1 to j
		 * dp[i][j] = No. of sequences with bottom tower size = i and height = j
		 * dp[0][i] = 1		No. of sequences with bottom tower size = 1 and height = i
		 * dp[i][0] = 1		No. of sequences with bottom tower size = i and height = 1
		 */
		long[][] dp2 = new long[m][n];
		long result2;
		for (int i=0;i<m;i++) {
			dp2[i][0] = 1;
		}
		for (int i=1;i<n;i++) {
			dp2[0][i] = 1;
		}
		for (int i=1;i<m;i++) {
			for (int j=1;j<n;j++) {
				counter = 0;
				for (int x=0;x<i;x++) {
					for (int y=0;y<j;y++) {
						counter = counter + dp2[x][y];
					}
				}
				// Here we add 1 as all tiles can be of size i
				dp2[i][j] = counter+1;
			}
		}
		result2 = 0;
		for (int i=0;i<m;i++) {
			result2 = result2 + dp2[i][n-1];
		}
		
		/**
		 * Now let us generalize our solution to the original problem
		 * dp[i][j] = sum(sum(dp[i-x][j-y])) where x = 1 to i and (y = 1 to z where z = min (k,j))
		 * dp[0][i] = 1		No. of sequences with bottom tower size = 1 and height = i if (i < k)
		 * dp[0][i] = 0		No. of sequences with bottom tower size = 1 and height = i if (i >= k)
		 * dp[i][0] = 1		No. of sequences with bottom tower size = i and height = 1
		 */
		long[][] dp3 = new long[m][n];
		long result3;
		for (int i=0;i<m;i++) {
			dp3[i][0] = 1;
		}
		for (int i=1;i<n && i<k;i++) {
			dp3[0][i] = 1;
		}
		for (int i=k;i<n;i++) {
			dp3[0][i] = 0;
		}
		for (int i=1;i<m;i++) {
			for (int j=1;j<n;j++) {
				counter = 0;
				for (int x=0;x<i;x++) {
					for (int y=1;y<=j && y<=k;y++) {
						counter = counter + dp3[x][j-y];
					}
				}
				// Here we add 1 as all tiles can be of size i if height <= k
				dp3[i][j] = counter + ((j < k) ? 1 : 0);
			}
		}
		result3 = 0;
		for (int i=0;i<m;i++) {
			result3 = result3 + dp3[i][n-1];
		}
		
		/**
		 * The above code gives us the solution using dp approach.
		 * But the complexity is O(m*n*m*k) this is due to 4 for loops.
		 * Can we precompute the result and store so that the inner 2 loops can be removed.
		 * This is by using store as sum matrix
		 */
		long[][] dp4 = new long[m][n];
		long[][] store = new long[m][n];
		long result4;
		for (int i=0;i<m;i++) {
			dp4[i][0] = 1;
			store[i][0] = i+1;
		}
		for (int i=1;i<n && i<k;i++) {
			dp4[0][i] = 1;
			store[0][i] = i+1;
		}
		for (int i=k;i<n;i++) {
			dp4[0][i] = 0;
			store[0][i] = k;
		}
		for (int i=1;i<m;i++) {
			for (int j=1;j<n;j++) {
				if (j <= k) {
					counter = store[i-1][j-1];
				} else {
					counter = store[i-1][j-1] - store[i-1][j-k-1];
				}
				// Here we add 1 as all tiles can be of size i if height <= k
				dp4[i][j] = counter + ((j < k) ? 1 : 0);
				store[i][j] = store[i-1][j] + store[i][j-1] - store[i-1][j-1] + dp4[i][j];
			}
		}
		result4 = 0;
		for (int i=0;i<m;i++) {
			result4 = result4 + dp4[i][n-1];
		}
		// This is currently O(m*n) time and O(m*n) space complexity
		// This can further be reduced to O(m*n) time with O(n) space complexity
		return result4;
	}
}
