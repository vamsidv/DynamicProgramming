package dynamic.programming;

import java.util.HashMap;
import java.util.Scanner;

public class ChoiceOfArea {
	
	private static HashMap<Pair, Long> dict;

	public static void main(String[] args) {
		/**
		 * https://www.geeksforgeeks.org/game-theory-choice-area/
		 * Consider a game, in which you have two types of powers, A and B and there are 3 types of Areas X, Y and Z.
		 * Every second you have to switch between these areas, each area has specific properties by which your power A and power B increase or decrease.
		 * We need to keep choosing areas in such a way that our survival time is maximized.
		 * Survival time ends when any of the powers, A or B reaches less than 0.
		 * 
		 * Example: A = 20, B = 8, X = (3, 2), Y = (-5, -10), Z = (-20, 5)
		 * Ans: 5	(X -> Z -> X -> Y -> X)
		 */
		
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int testCase=1;testCase<=testCases;testCase++) {
			dict = new HashMap<Pair, Long>();
			Pair powers = new Pair();
			Pair x = new Pair();
			Pair y = new Pair();
			Pair z = new Pair();
			powers.setaArea(sc.nextInt());
			powers.setbArea(sc.nextInt());
			x.setaArea(sc.nextInt());
			x.setbArea(sc.nextInt());
			y.setaArea(sc.nextInt());
			y.setbArea(sc.nextInt());
			z.setaArea(sc.nextInt());
			z.setbArea(sc.nextInt());
			
			System.out.println("#" + testCase + ": " + GetMax(CalcResult(new Pair(powers, x), x, y, z, 1),
					CalcResult(new Pair(powers, y), x, y, z, 2), CalcResult(new Pair(powers, z), x, y, z, 3)));
		}
		sc.close();
	}

	private static long CalcResult(Pair powers, Pair x, Pair y, Pair z, int pos) {
		if ((powers.getaArea() < 0) || (powers.getbArea() < 0)) {
			return 0;
		}
		powers.setPos(pos);
		if(dict.containsKey(powers)) {
			return dict.get(powers);
		}
		long a = 0;
		long b = 0;
		long c = 0;
		if (pos != 1) {
			Pair aPowers = new Pair(powers, x);
			a = CalcResult(aPowers, x, y, z, 1);
		}
		if (pos != 2) {
			Pair bPowers = new Pair(powers, y);
			b = CalcResult(bPowers, x, y, z, 2);
		}
		if (pos != 3) {
			Pair cPowers = new Pair(powers, z);
			c = CalcResult(cPowers, x, y, z, 3);
		}
		dict.put(powers, 1+GetMax(a, b, c));
		return dict.get(powers);
	}
	
	private static long GetMax(long a, long b, long c) {
		if ((a >= b) && (a >= c)) {
			return a;
		}
		if ((b >= a) && (b >= c)) {
			return b;
		}
		return c;
	}
}

class Pair {
	private int aArea;
	private int bArea;
	private int pos;
	
	public Pair(Pair powers, Pair x) {
		this.aArea = powers.getaArea() + x.getaArea();
		this.bArea = powers.getbArea() + x.getbArea();
	}
	public Pair() {
	}
	public int getaArea() {
		return aArea;
	}
	public void setaArea(int aArea) {
		this.aArea = aArea;
	}
	public int getbArea() {
		return bArea;
	}
	public void setbArea(int bArea) {
		this.bArea = bArea;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
}
