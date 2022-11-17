package com.transportation.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static final int leftLimit = 48;
	private static final int rightLimit = 122;

	/**
	 * generate random string between number {0-9} and characters {A,a - Z,z}
	 * @param codeLength size of Random string "if param less than 1 will return empty string"
	 * @return random string
	 */
	public static String generateRandomString(Integer codeLength) {
		if (codeLength == null || codeLength <= 0) {
			return "";
		}
		return new Random().ints(leftLimit, rightLimit+1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(codeLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}

	public static String matches(String string,String pattern){
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(string);
		if(m.find()){
			return m.group();
		}return "";
	}



}
