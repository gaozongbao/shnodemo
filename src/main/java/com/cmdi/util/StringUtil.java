package com.cmdi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * */
public class StringUtil {
	public static String StringReplaceByParameters(String old, String before, Object ... parameters) {
		int number = getNumber(old, before);
		if(number == 0 || number != parameters.length) {
			return null;
		}
			
		else {
			for(int i = 0; i < number; i++) {
				old = old.replaceFirst(before, parameters[i].toString());
			}
			return old;
		}
	}
	
	public static int getNumber(String str, String child) {
		if(str == null || child == null)
			return 0;
		Pattern pattern = Pattern.compile(child);
		Matcher matcher = pattern.matcher(str);
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(StringReplaceByParameters("hello #,you are #", "#", "java","excellent"));
	}
}
