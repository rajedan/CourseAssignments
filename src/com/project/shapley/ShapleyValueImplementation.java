package com.project.shapley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ShapleyValueImplementation {
	static HashSet<String> mySet = new HashSet<>();
	static HashMap<String, Integer> allCoalitions = new HashMap<>();
	/**
	 * Contains the total contribution of each player in coalition
	 */
	static HashMap<String, Integer> playersTotal = new HashMap<>();
	/**
	 * Creates all unique permutations of the str String and 
	 * adds those into mySet
	 * @param prefix
	 * @param str
	 */
	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0) {
			//System.out.println(prefix);
			mySet.add(prefix);
		}
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
		}
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		System.out.print("Enter the number of players : ");
		int players = scn.nextInt();
		System.out.println("-----");
		// Make hashmap to store characteristric function values
		String str = "";
		for (int i = 0; i < Math.pow(2, players); i++) {
			str = changeBinaryToNumber(Integer.toBinaryString(i));
			System.out.print("Characteristic Function for coalition with players - " + (str.equals("0")?"No Player<Empty Coalition>":str) + " is : ");
			allCoalitions.put(str, scn.nextInt());
		}
		System.out.println("---");
		updateAllPlayerPermutations(players);
		for(String coalition : mySet){
			calculateTotalForEachPlayerInCoalition(coalition);
		}
		showShapleyValueOfEachPlayer();
		if (scn != null) {
			scn.close();
		}
	}
	private static void showShapleyValueOfEachPlayer() {
		DecimalFormat format = new DecimalFormat("##.00");
		for (String key : playersTotal.keySet()) {
			double val = (double)playersTotal.get(key)/mySet.size();
			System.out.println("Shapley value of Player "+key+" is : "+format.format(val));
		}
	}

	private static void updateAllPlayerPermutations(int noOfPlayers) {
		String mainPlayers = "";
		for (int i = 1; i <= noOfPlayers; i++){
			mainPlayers += i;
			playersTotal.put(Integer.toString(i), 0);
		}
		permutation("", mainPlayers);
	}

	/**
	 * This method takes an String of boolean representation and returns it's sorted string representation in decimal form
	 * <br>
	 * Example : <br>
	 * Input-10 Output-2
	 * <br>
	 * Input-110 Output-23(This string is sorted from 32 to 23 before returning)
	 * <br>
	 * Input-111 Output-123(sorted the string before returning from 321 to 123)
	 * <br>
	 * Input-1 Output-1
	 * <br>
	 * Input-11 Output-12
	 * <br>
	 * @param strNum
	 * @return
	 */
	private static String changeBinaryToNumber(String strNum) {
		if (strNum.equals("0")) {
			// System.out.println(strNum+":"+"0");
			return "0";
		}
		int count = strNum.length();
		String strRep = "";
		for (char ch : strNum.toCharArray()) {
			if (ch == '1') {
				strRep += count;
			}
			count--;
		}
		// System.out.println(strNum+":"+sortString(strRep));
		return sortString(strRep);
	}
	/**
	 * Sort a String
	 * <br>
	 * Example : String 231 will become 123 after sorting
	 * @param strRep
	 * @return Sorted integer of String form
	 */
	private static String sortString(String strRep) {
		char[] chr = strRep.toCharArray();
		Arrays.sort(chr);
		strRep = new String(chr);
		return strRep;
	}
	
	private static void calculateTotalForEachPlayerInCoalition(String coalition) {
		char[] charArray = coalition.toCharArray();
		String temp = "", chStr = "";
		for(char ch : charArray){
			if (temp.equals("")) {
				chStr = String.valueOf(ch);
				playersTotal.put(chStr, playersTotal.get(chStr)+allCoalitions.get(sortString(chStr)));
				temp = temp+chStr;
			} else {
				chStr = String.valueOf(ch);
				int val = allCoalitions.get(sortString(temp+chStr)) - allCoalitions.get(sortString(temp));
				playersTotal.put(chStr, playersTotal.get(chStr)+val);
				temp = temp+chStr;
			}
		}
	}
}