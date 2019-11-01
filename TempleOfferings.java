package dynamic.programming;

import java.util.Scanner;

public class TempleOfferings {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/temple-offerings/
		 * *** NOTE: THIS IS AN INTERESTING PROBLEM. THE WAT TO THINK IS TO ANALYZE THE PROBLEM PROPERLY ***
		 * Consider a devotee wishing to give offerings to temples along a mountain range.
		 * The temples are located in a row at different heights.
		 * Each temple should receive at least one offering.
		 * If two adjacent temples are at different altitudes,
		 * then the temple that is higher up should receive more offerings than the one that is lower down.
		 * If two adjacent temples are at the same height, then their offerings relative to each other does not matter.
		 * Given the number of temples and the heights of the temples in order,
		 * find the minimum number of offerings to bring.
		 * 
		 * Input  : 3
		 * 			1 2 2
		 * Output : 4
		 * All temples must receive at-least one offering. 
		 * Now, the second temple is at a higher altitude compared to the first one. Thus it receives one extra offering. 
		 * The second temple and third temple are at the same height, so we do not need to modify the offerings. 
		 * Offerings given are therefore: 1, 2, 1 giving a total of 4.
		 * 
		 * Input  : 6
		 *          1 4 3 6 2 1
		 * Output : 10
		 * We can distribute the offerings in the following way, 1, 2, 1, 3, 2, 1.
		 * The second temple has to receive more offerings than the first due to its height being higher. 
		 * The fourth must receive more than the fifth, which in turn must receive more than the sixth.
		 * Thus the total becomes 10.
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
			System.out.println("#" + testCase + ": " + CalcResultOn(data, n));
		}
		sc.close();
	}

	/**
	 * Consider the input as 1 4 3 6 2 1
	 * For the element "6", what would be the offering value??
	 * 
	 *   4   6
	 *  / \ / \
	 * 1   3   2
	 *          \
	 *           1
	 * the offering value is 3 which is due to the elements 2,1 on the right which are in decreasing order from 6 towards right.
	 * 
	 * For the element 2, the offering value is 2 which is due to element 1 on the right.
	 * 
	 * From this can we observe a pattern??
	 * The pattern is that for any element i, the offering value = (1 + max(a,b)) where a, b are defined as follows
	 * a = number of immediate elements that form decreasing order from i towards left
	 * b = number of immediate elements that form decreasing order from i towards right
	 * 
	 * The following is a naive solution for the same
	 */
	private static long CalcResult(int[] data, int n) {
		long result = 0;
		int a,b;
		for (int i=0;i<n;i++) {
			a = b = 0;
			for (int j=i-1;j>=0;j--) {
				if (data[j] < data[j+1]) {
					a++;
				} else {
					break;
				}
			}
			for (int j=i+1;j<n;j++) {
				if (data[j] < data[j-1]) {
					b++;
				} else {
					break;
				}
			}
			result = result + ((a > b) ? a : b) + 1;
		}
		return result;
	}

	/**
	 * For the above naive solution, if we observe closely, 
	 * we can notice that for each element we calculate a and b which is causing O(n^2) complexity.
	 * If we can get the values of a and b in O(1) time, the overall complexity will be O(n).
	 * Therefore we need to pre-compute the values of a and b in less than O(n) time for all elements combined.
	 */
	private static long CalcResultOn(int[] data, int n) {
		int[] a = new int[n];
		int[] b = new int[n];
		long result = 0;
		a[0] = 0;
		for (int i=1;i<n;i++) {
			a[i] = (data[i] > data[i-1]) ? (a[i-1]+1) : 0;
		}
		b[n-1] = 0;
		for (int i=n-2;i>=0;i--) {
			b[i] = (data[i] > data[i+1]) ? (b[i+1]+1) : 0;
		}
		for (int i=0;i<n;i++) {
			result = result + 1 + ((a[i] > b[i]) ? a[i] : b[i]);
		}
		return result;
	}
}
