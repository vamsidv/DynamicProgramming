package dynamic.programming;

import java.util.Scanner;

public class SuperUglyNumber {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/super-ugly-number-number-whose-prime-factors-given-set/
		 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list.
		 * Given a number n, the task is to find n’th Super Ugly number.
		 * It may be assumed that given set of primes is sorted. Also, first Super Ugly number is 1 by convention.
		 * 
		 * Input  : primes[] = [2, 5]
		 * 			n = 5
		 * Output : 8
		 * Super Ugly numbers with given prime factors are 1, 2, 4, 5, 8, ...
		 * Fifth Super Ugly number is 8
		 * 
		 * Input  : primes[] = [2, 3, 5]
		 * 			n = 50
		 * Output : 243
		 * 
		 * Input  : primes[] = [3, 5, 7, 11, 13]
		 * 			n = 9
		 * Output: 21
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = sc.nextInt();
			int[] data = new int[n];
			for (int i=0;i<n;i++) {
				data[i] = sc.nextInt();
			}
			int k = sc.nextInt();
			
			System.out.println("#" + testCase + ": " + CalcResult(data, n, k));
		}
		sc.close();
	}

	private static long CalcResult(int[] data, int n, int k) {
		int[] store = new int[k];
		store[0] = 1;
		int[] ptrs = new int[n];
		int index;
		for (int i=1;i<k;i++) {
			index = GetMinIndex(store, ptrs, data, n);
			store[i] = data[index] * store[ptrs[index]];
			ptrs[index]++;
			if (store[i] == store[i-1]) {
				i--;
			}
		}
		return store[k-1];
	}

	private static int GetMinIndex(int[] store, int[] ptrs, int[] data, int n) {
		long min = data[0] * store[ptrs[0]];
		long temp;
		int index = 0;
		for (int i = 1; i<n; i++) {
			temp = data[i] * store[ptrs[i]];
			index = (min < temp) ? index : i;
			min = (min < temp) ? min : temp;
		}
		return index;
	}
}
