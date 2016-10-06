package util;

import java.util.Scanner;

/**
 * Prompts for and then validates console-based input. This program is
 * based on code written by Professor John Phillips, who based his program
 * on code from Murach's Java SE 6.
 * 
 * @author Jason Whiting
 * @version 2016-10-06
 */
public class Validator {

	/**
	 * Prompts the user with a message and then retrieves what the user
	 * types as a String.
	 * 
	 * @param sc
	 * @param prompt
	 * @return
	 */
	public static String getLine(Scanner sc, String prompt) {
		System.out.print(prompt);
		return sc.nextLine();
	}
	
	/**
	 * Prompts the user with a message and then retrieves what the user
	 * types as a String. The 'regex' is a String that contains a regular
	 * expression. The message repeats until the user enters a correct 
	 * value. For example, a regular expression of "^[1234]$" would require
	 * a '1' or a '2' or a '3' or a '4' to be entered.
	 * 
	 * @param sc
	 * @param prompt
	 * @param regex
	 * @return
	 */
	public static String getLine(Scanner sc, String prompt, String regex) {
		boolean isValid = false;
		String s = "";
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextLine()) {
				s = sc.nextLine();
				if (s.matches(regex)) {
					isValid = true;
				} else {
					System.out.println("\nERROR! Invalid input. Try again.\n");
				}
			}
		}
		return s;
	}
	
	/**
	 * Prompts the user with a message and then retrieves what the user types 
	 * as an integer.
	 * 
	 * @param sc
	 * @param prompt
	 * @return
	 */
	public static int getInt(Scanner sc, String prompt) {
		boolean isValid = false;
		int i = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("\nERROR! Invalid integer value. Try again.\n");
			}
			sc.nextLine();
		}
		return i;
	}
	
	public static int getInt(Scanner sc, String prompt, int min, int max) {
		boolean isValid = false;
		int i = 0;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i < min) {
				System.out.println("\nERROR! Must be greater than " + min + ".\n");
			} else if (i > max) {
				System.out.println("\nERROR! Must be less than " + max +".\n");
			} else {
				isValid = true;
			}
		}
		return i;
	}
}
