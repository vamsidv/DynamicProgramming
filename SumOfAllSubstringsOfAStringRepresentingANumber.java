package DP;

import java.util.Scanner;

public class SumOfAllSubstringsOfAStringRepresentingANumber {

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/sum-of-all-substrings-of-a-string-representing-a-number/
		 * Given a integer represented as a string, we need to get the sum of all possible substrings of this string.
		 * Input  : num = “1234”
		 * Output : 1670
		 * Sum = 1 + 2 + 3 + 4 + 12 + 23 + 34 + 123 + 234 + 1234 = 1670
		 * 
		 * Input  : num = “421”
		 * Output : 491
		 * Sum = 4 + 2 + 1 + 42 + 21 + 421 = 491
		 */

		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		sc.nextLine();
		for (int testCase = 1; testCase <= testCases; testCase++) {
			String s = sc.nextLine();
			System.out.println("#" + testCase + ": " + CalcResult(s));
		}
		sc.close();
	}

	/*
		    a
		    b
		   ab
		    c
		   bc
		  abc
		    d
		   cd
		  bcd
		 abcd
		    e
		   de
		  cde
		 bcde
		abcde
		
		a					(5th digit value)
		 bba				(4th digit value = b+b+a)
		  cccbba			(3rd digit value = d+d+d+d+c+c+c+b+b+a)
		   ddddcccbba		(4th digit value)
		    eeeeeddddcccbba	(5th digit value)
	 */
	private static long CalcResult(String s) {
		int l = s.length();
		long[] dp = new long[l];
		long temp, result;
		for (int i=0;i<l;i++) {
			temp = 0;
			for (int j=i;j>=0;j--) {
				temp = temp + (Integer.parseInt("" + s.charAt(j)) * (j+1));
			}
			dp[i] = temp;
		}
		result = dp[0];
		for (int i=1;i<l;i++) {
			result = (result * 10) + dp[i];
		}
		return result;
	}
}
