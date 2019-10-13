package dynamic.programming;

import java.util.Scanner;

public class MinimumCostToFillGivenWeightInABag {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/minimum-cost-to-fill-given-weight-in-a-bag/
		 * You are given a bag of size W kg and you are provided costs of packets different weights of oranges in array cost[] 
		 * where cost[i] is basically cost of ‘i’ kg packet of oranges. Where cost[i] = -1 means that ‘i’ kg packet of orange is unavailable
		 * For understanding purposes, array indices starts with 1
		 * Find the minimum total cost to buy exactly W kg oranges and if it is not possible to buy exactly W kg oranges then print -1.
		 * It may be assumed that there is infinite supply of all available packet types.
		 * 
		 * Input  : W = 5, cost[] = {20, 10, 4, 50, 100}
		 * Output : 14
		 * We can choose two oranges to minimize cost. First orange of 2Kg and cost 10. Second orange of 3Kg and cost 4.
		 * 
		 * Input  : W = 5, cost[] = {1, 10, 4, 50, 100}\
		 * Output : 5
		 * We can choose five oranges of weight 1 kg.
		 * 
		 * Input  : W = 5, cost[] = {1, 2, 3, 4, 5}
		 * Output : 5
		 * We choose packet of 5kg having cost 5 for minimum cost to get 5Kg oranges.
		 * 
		 * Input  : W = 5, cost[] = {-1, -1, 4, 5, -1}
		 * Output : -1
		 * Packets of size 1, 2 and 5 kg are unavailable because they have cost -1. 
		 * Cost of 3 kg packet is 4 Rs and of 4 kg is 5 Rs. Here we have only weights 3 and 4 so by using these two we can not make exactly W kg weight,
		 * therefore answer is -1.
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int w = sc.nextInt();
			int[] data = new int[n];
			for (int i=0;i<n;i++) {
				data[i] = sc.nextInt();
			}
			System.out.println("#" + testCase + ": " + CalcResult(data, n, w));
		}
		sc.close();
	}

	private static long CalcResult(int[] data, int n, int w) {
		return 0;
	}
}
