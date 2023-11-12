package main;

import java.util.regex.Pattern;

public class Utilities {
	public static boolean checkNumber(String number, String value) throws Exception {
			if(Pattern.matches("\\d+", number)) {
			return true;
		}
		throw new Exception("*" + value + " must be a number.");
	}
	
	public static boolean checkString(String str, String value) throws Exception {
		if(Pattern.matches("[A-Za-z\\s]*", str)) {
			return true;
		}
		throw new Exception("*" + value + " must be a-z character.");
	}
	
	public static boolean checkEmail(String email, String value) throws Exception {
		if(Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", email)) {
			return true;
		}
		throw new Exception("*" + value + " is invalid.");
	}
	
	public static boolean checkPhoneNumber(String phoneNumber, String value) throws Exception {
		if(Pattern.matches("09\\d{8,9}", phoneNumber)) {
			return true;
		}
		throw new Exception("*" + value + " is invalid.");
	}
}
