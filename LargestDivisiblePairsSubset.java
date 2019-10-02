package dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LargestDivisiblePairsSubset {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/largest-divisible-pairs-subset/
		 * Given an array of n distinct elements,
		 * find length of the largest subset such that every pair in the subset is such that the larger element of the pair is divisible by smaller element.
		 * 
		 * Input : arr[] = {10, 5, 3, 15, 20}
		 * Output : 3
		 * Explanation: The largest subset is 10, 5, 20.
		 * 
		 * Input : arr[] = {18, 1, 3, 6, 13, 17}
		 * Output : 4
		 * Explanation: The largest subset is 18, 1, 3, 6
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase=1;testCase<=testCases;testCase++) {
			int s = sc.nextInt();
			int data[] = new int[s];
			for (int i=0;i<s;i++) {
				data[i] = sc.nextInt();
			}
			System.out.println("#" + testCase + ": " + CalcResult(data,s));
		}
		sc.close();
	}

	private static int CalcResult(int[] data, int s) {
		ArrayList<Integer> dataArray = (ArrayList<Integer>) Arrays.stream(data).boxed().collect(Collectors.toList());
		Collections.sort(dataArray);
		// We can alternatively use Arrays.sort(a)
		int result[] = new int[s];
		for (int i=0;i<s;i++) {
			result[i] = 1;
		}
		for (int i=s-2;i>=0;i--) {
			for (int j=i+1;j<s;j++) {
				if ((dataArray.get(j)%dataArray.get(i) == 0) && (result[i] < (result[j] + 1))) {
					result[i] = result[j] + 1;
				}
			}
		}
		int max = 0;
		for (int i=0;i<s;i++) {
			max = (result[i] > max) ? result[i] : max;
		}
		// Alternatively
		return Arrays.stream(result).max().getAsInt();
	}
}
