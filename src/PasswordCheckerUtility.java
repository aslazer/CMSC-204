/**
 * This utility class checks to see if a password meets the required qualifications
 * @author AlexSlazer
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BooleanSupplier;
import java.util.regex.*;

public class PasswordCheckerUtility {
	
	private ArrayList<String> passwordsFromFile; // to hold passwords read from file

	public PasswordCheckerUtility() {};
	
	/**
	 * this method sees if two password entries match
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		
		if(!password.equals(passwordConfirm)){
			
			throw new UnmatchedException("Passwords do not match");
		}
	}
	/**
	 * this method sees if two password entries match
	 * @param password
	 * @param passwordConfirm
	 * @return
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		
		if(password.equals(passwordConfirm)){
			
			return true;
		}
		else {
			
			return false;
		}
	}
	/**
	 * this method creates an ArrayList of invalid passwords
	 * @param passwords
	 * @return
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		
		int size = passwords.size();
		
		ArrayList<String> invalidPasswords = new ArrayList<>();
		
		for(int i = 0; i < size; i++) { // checking for invalid passwords
			
			try {
				if(!isValidLength(passwords.get(i))) {}
				else if(!hasUpperAlpha(passwords.get(i))) {}
				else if(!hasLowerAlpha(passwords.get(i))) {}
				else if(!hasDigit(passwords.get(i))) {}
				else if(!hasSpecialChar(passwords.get(i))) {}
				else if(hasSameCharInSequence(passwords.get(i))) {}
				else if(hasBetweenSixAndNineChars(passwords.get(i))) {}
			} catch (LengthException ex) {
				
				invalidPasswords.add(passwords.get(i) + " -> The password must be at least 6 characters long");
			} catch (NoUpperAlphaException ex) {
				
				invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one uppercase alphabetic character");
			} catch (NoLowerAlphaException ex) {
				
				invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one lower case alphabetic character");
			} catch (NoDigitException ex) {
				
				invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one digit");
			} catch (NoSpecialCharacterException ex) {
				
				invalidPasswords.add(passwords.get(i) + " -> The password must contain at least one special character");
			} catch (InvalidSequenceException ex) {
				
				invalidPasswords.add(passwords.get(i) + " -> The password cannot contain more than two of the same character in sequence");
			} catch (WeakPasswordException ex) {
				
				passwords.add(i, " -> The password is OK but weak - it contains fewer than 10 characters");
			}
			
		}
		
		return invalidPasswords; // returning invalid passwords
	}
	/**
	 * this method checks to see if a password meets the length requirement of at least 6
	 * @param password
	 * @return
	 * @throws LengthException
	 */
	public static boolean isValidLength(String password) throws LengthException {
		
		boolean valid = false;
			
		try {

			if(password.length() < 6) {
				
				throw new LengthException("The password must be at least 6 characters long");
			}
			else {
				
				valid = true;
			}
		}
		catch(LengthException ex) {
			
			throw new LengthException("The password must be at least 6 characters long");
		}   
			
		return valid;	
	}
	/**
	 * this method checks to see if a password has an upper case character
	 * @param password
	 * @return
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		
		boolean valid = false;
		
		try { 

			int count = 0;

			for(int j = 65; j <= 90; j++) { // checking for upper

				char c=(char)j;

				String str1 = Character.toString(c);

				if(password.contains(str1))
				{
					count = 1;
					valid = true;
				}
			}

			if(count == 0)
			{
				valid = false;
				throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
			}
		} catch(NoUpperAlphaException ex) {
			
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
		}

		return valid;
	}
	/**
	 * this method checks to see if a password has a lower case character
	 * @param password
	 * @return
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
		
		boolean valid = false;
		
		try { 

			int count = 0;

			for(int j = 90; j <= 122; j++) { // checking for lower case

				char c=(char)j;

				String str1 = Character.toString(c);

				if(password.contains(str1))
				{
					count = 1;
					valid = true;
				}
			}

			if(count == 0)
			{
				valid = false;
				throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");

			}
		} catch(NoLowerAlphaException ex) {
			
			throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
		}
		
		return valid;
	}
	/**
	 * this method checks to see if a password has a numeric character
	 * @param password
	 * @return
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException {
		
		boolean valid = false;
		
		try { 

			int count = 0;

			for(int j = 0; j <= 9; j++) { // checking for numeric

				String str1 = Integer.toString(j);

				if(password.contains(str1))
				{
					count = 1;
					valid = true;
				}
			}

			if(count == 0)
			{
				valid = false;
				throw new NoDigitException("The password must contain at least one digit");

			}
		} catch(NoDigitException ex) {
			
			throw new NoDigitException("The password must contain at least one digit");
		}
		
		return valid;
	}
	/**
	 * this method checks to see if a password has a special character
	 * @param passwords
	 * @return
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(String passwords) throws NoSpecialCharacterException {
		
		boolean valid = false;
		
		try {

			for(int j = 0; j < passwords.length(); j++) { // checking for special

				if(passwords.charAt(j) == ' ' || passwords.charAt(j) == '!' || passwords.charAt(j) == '"' ||
						passwords.charAt(j) == '#' || passwords.charAt(j) == '$' || passwords.charAt(j) == '%' ||
						passwords.charAt(j) == '&' || passwords.charAt(j) == '\'' || passwords.charAt(j) == '(' ||
						passwords.charAt(j) == ')' || passwords.charAt(j) == '*' || passwords.charAt(j) == '+' ||
						passwords.charAt(j) == ',' || passwords.charAt(j) == '-' || passwords.charAt(j) == '.' ||
						passwords.charAt(j) == '/' || passwords.charAt(j) == ':' || passwords.charAt(j) == ';' ||
						passwords.charAt(j) == '<' || passwords.charAt(j) == '=' || passwords.charAt(j) == '>' ||
						passwords.charAt(j) == '?' || passwords.charAt(j) == '@' || passwords.charAt(j) == '[' ||
						passwords.charAt(j) == '\\' || passwords.charAt(j) == ']' || passwords.charAt(j) == '^' ||
						passwords.charAt(j) == '_' || passwords.charAt(j) == '`' || passwords.charAt(j) == '{' || 
						passwords.charAt(j) == '|' || passwords.charAt(j) == '}' || passwords.charAt(j) == '~') {

					valid = true;
				}
			}
			if(!valid)
			{
			
				throw new NoSpecialCharacterException("The password must contain at least one special character");

			}
			
		} catch(NoSpecialCharacterException ex) {
			
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		
		return valid;
	}
	/**
	 * this method checks to see if a password has more than two consecutive characters
	 * @param password
	 * @return
	 * @throws InvalidSequenceException
	 */
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException {
		
		boolean valid = false;
		
		try { 

			int count = 0;

			for(int j = 1; j < password.length(); j++) { // two consecutive chars

				String current = password;
				
				if(current.charAt(j) == current.charAt(j-1)) {
					
					count++;
					valid = true;
				}
			}
			
			if(count > 2)
			{
				
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");

			}
		} catch(InvalidSequenceException ex) {
			
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
		}

		
		return valid;
	}
	/**
	 * this method checks to see if a password is a weak password
	 * @param password
	 * @return
	 * @throws WeakPasswordException
	 */
	public static boolean hasBetweenSixAndNineChars(String password) throws WeakPasswordException {
		
		boolean valid = false;
		
		try { 

			int count = 0;

			if(password.length() > 5 && password.length() < 10) { // checking for weak
				
				valid = true;
				count = 1;
			}
			if(count == 1)
			{
				
				throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters");
			}
		} catch(WeakPasswordException ex) {
			
			throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters");
		}
		
		return valid;
	}
	/**
	 * this method checks to see if a password is valid
	 * @param password
	 * @return
	 */
	public static boolean isValidPassword(String password) {
		
		boolean invalid = false;
		
		try {
			if(isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password) && hasSpecialChar(password) && !hasSameCharInSequence(password)) {}
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
				| NoSpecialCharacterException | InvalidSequenceException e) {
			
			invalid = true;
			
		}
		
		return invalid;
	}
	/**
	 * this method gets passwordsFromFile array
	 * @return
	 */
	public ArrayList<String> getPasswords() {
		return passwordsFromFile;
	}
	/**
	 * this method sets passwordsFromFile array
	 * @param file
	 */
	public void setPasswordsFromFile(File file) {
		
		passwordsFromFile.clear();
		
		String fileName = file.getName();
		Scanner scan = new Scanner(fileName);
		
		while(scan.hasNext()) { // reading in passwords from file
			
			passwordsFromFile.add(scan.nextLine());
		}
		
		scan.close();
	}
}