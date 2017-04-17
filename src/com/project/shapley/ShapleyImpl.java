package com.project.shapley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ShapleyImpl {
	
	static final int MIN = 1;
	static final int MAX = 100;
	
	static HashMap<String, Integer> allCoal = new HashMap<>();
	
	private static int factorial(int number) {
		if (number == 0 || number == 1) {
			return 1;
		}
		return number * factorial(number-1);
	}
	/**
	 * 
	 * @param n Number of member elements in the game or in grand coalition
	 * @param ofWhichKey The key for which we are calculating shapley value
	 * @return
	 */
	private static double calculateShapleyValue(int n, int ofWhichKey) {
		int divident = 1/factorial(n);
		
		double noOfLoop = Math.pow(2, n-1);
		
		for (int i = 0; i < noOfLoop; i++) {
			//calculateInsideStuff(n, );
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		
		System.out.print("Enter Number of CPs : ");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		
		int noOfCP = Integer.parseInt(bfr.readLine());
		
		int noOfCoalition = (int) Math.pow(2, noOfCP);//2 ^ noOfCP;
		
		for (int i = 0; i < noOfCoalition; i++) {
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
			allCoal.put(Integer.toBinaryString(i), randomNum);
		}
		System.out.println();
		for (int i = 0; i < allCoal.size(); i++) {
			System.out.println(Integer.toBinaryString(i)+":"+allCoal.get(Integer.toBinaryString(i)));
		}
		
		if (bfr != null) {
			bfr.close();
		}
		permutation("", "1234");
	}
	
	private static void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0) System.out.println(prefix);
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
}
