package dynamic.programming;

import java.util.Scanner;

public class SubsetWithSumDivisibleByM {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/subset-sum-divisible-m/
		 * Given a set of non-negative distinct integers, and a value m, determine if there is a subset of the given set with sum divisible by m. 
		 * Input Constraints:
		 * 		Size of set i.e., n <= 1000000, m <= 1000
		 * Input : arr[] = {3, 1, 7, 5}, m = 6
		 * Output : YES
		 * Input : arr[] = {1, 6}, m = 5
		 * Output : NO
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase=1;testCase<=testCases;testCase++) {
			int s = sc.nextInt();
			int n = sc.nextInt();
			int data[] = new int[s];
			for (int i=0;i<s;i++) {
				data[i] = sc.nextInt();
			}
			
			System.out.println("#" + testCase + ": " + CalcResult(data,s,n));
		}
		sc.close();
	}

	private static String CalcResult(int[] data, int s, int n) {
		/*
		 * let 'n' be the size of the set and 'm' be the given number
		 * Statement:
		 * 		if n > m then there must be a subset whose sum of elements is divisible by m
		 * Proof:
		 * 		Let the elements of the given set be E1, E2, E3,..., En
		 * 		Now we construct the sum array using the above elements. i.e. ith element = sum of first i elements.
		 * 		Therefore we get the result as S1, S2, S3,..., Sn
		 * 		If we take modulo of each element of the above set by m then we get
		 * 		M1, M2, M3,..., Mn
		 * 		According to pigeon hole principle, atleast one of above elements are repeated.
		 * 		For example consider M4 == M9
		 * 		which means that sum of elements from M4 to M9 is divisible by m
		 */
		if (n < data.length) {
			return "YES";
		}
		return (CalcResultBPOn(data, s, n, 0)==1) ? "YES" : "NO";
	}
	
	private static long CalcResultBPOn(int[] data, int s, int n, int index) {
		if (s == 0) {
	        return 0;
	    }
		long a[] = new long[n+1];
		a[0] = 1;
		for (int i=1;i<=n;i++) {
			a[i] = 0;
		}
		if (data[0] <= n) {
    		a[data[0]] = 1;
		}
		for (int j=1;j<s;j++) {
			// It is important to understand why we are looping in backward direction
			for (int i=n;i>0;i--) {
				a[i] = ((a[i] == 1) || (i-data[j] >= 0 && a[i-data[j]] == 1)) ? 1 : 0;
			}
		}
		return a[n];
	}
}
