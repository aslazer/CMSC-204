/**
 * 
 * @author Alex Slazer
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class StudentTest {
	ArrayList<String> invalidPasswordsArray;
	ArrayList<String> validPasswordsArray;
	String password = "password";
	String passwordConfirm = "paSsword";
	String allCaps = "PASSWORD";
	String withDigit = "Password7";
	String withSpecialChar = "Password!";
	String withNoDuplicate = "Pasword!";
	String between6And9Chars = "password";
	String longPassword = "passwordpasswordpassword";

	@BeforeEach
	void setUp() throws Exception {
		String[] containsInvalidPwd = {"password", "pAssword7$", "passssword"};
		invalidPasswordsArray = new ArrayList<String>();
		invalidPasswordsArray.addAll(Arrays.asList(containsInvalidPwd));		
		
		String[] allValidPasswords = {"Valid1$", "Valid2$$", "Valid3$7"};
		validPasswordsArray = new ArrayList<String>();
		validPasswordsArray.addAll(Arrays.asList(allValidPasswords));		
	}

	@AfterEach
	void tearDown() throws Exception {
		invalidPasswordsArray = null;
		validPasswordsArray= null;
	}

	@Test
	void testComparePasswords() {
		Throwable exception = assertThrows(UnmatchedException.class, new Executable() {			
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.comparePasswords(password, passwordConfirm);				
			}
		});
		
		assertEquals("Passwords do not match", exception.getMessage());
	}
	
	@Test 
	void testComparePasswordsWithReturn() {
		assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
		assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
	}	
	
	@Test
	void testValidLengthValid() {
		try {
			assertTrue(PasswordCheckerUtility.isValidLength("Beautiful"));
		} catch (LengthException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testHasUpperAlphaValid() {
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHasLowerAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(password));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveLowerAlpha() {
		Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha(allCaps);
			}			
		});
		assertEquals("The password must contain at least one lower case alphabetic character", exception.getMessage());
		
	}
	
	@Test
	public void testHasDigit() {
		try {
			assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
		} catch (NoDigitException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveDigit() {
		Throwable exception = assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit(password);
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());		
	}
	
	@Test
	public void testHasSpecialChar() {
		try {
			assertTrue(PasswordCheckerUtility.hasSpecialChar(withSpecialChar));
		} catch (NoSpecialCharacterException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveSpecialChar() {
		Throwable exception = assertThrows(NoSpecialCharacterException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSpecialChar(password);
			}			
		});
		assertEquals("The password must contain at least one special character", exception.getMessage());		
	}
	
	@Test
	public void testHasSameCharInSequence() {
		Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSameCharInSequence("AAAbb@123");
			}			
		});
		assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());	
	}
	
	@Test
	public void testDoesNotHaveSameCharInSequence() {
		try {
			assertTrue(PasswordCheckerUtility.hasSpecialChar(withNoDuplicate));
		} catch (NoSpecialCharacterException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testHasBetweenSixAndNineChars() {
		try {
			assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(between6And9Chars));
		} catch (WeakPasswordException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
		try {
			assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(withSpecialChar));
		} catch (WeakPasswordException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		try {
			assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars(longPassword));
		} catch (WeakPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetValidPasswords() {
		
		ArrayList<String> results;
	
		results = PasswordCheckerUtility.getInvalidPasswords(validPasswordsArray);
		
		for(int i = 0; i < results.size(); i++) {
			
			System.out.println(results.get(i));
		}
		assertTrue(results.isEmpty());
	}

}