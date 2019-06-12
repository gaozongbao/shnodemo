package com.cmdi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The Class MD5Util.
 */
public class MD5Util {

	/**
	 * Check m d5.
	 * 
	 * @param in
	 *            the in
	 * @param orimd5
	 *            the orimd5
	 * 
	 * @return true, if check m d5
	 * 
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	public static boolean checkMD5(String in, String orimd5)
			throws NoSuchAlgorithmException {
		if (in == null) {
			return orimd5 == null;
		}
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(in.getBytes());
		byte[] bytes = md5.digest();

		return byteArrayToHex(bytes).equals(orimd5);
	}

	/**
	 * To m d5.
	 * 
	 * @param in
	 *            the in
	 * 
	 * @return the string
	 * 
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	public static String toMD5(String in) throws NoSuchAlgorithmException {
		if (in == null) {
			return null;
		}
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(in.getBytes());
		byte[] bytes = md5.digest();

		return byteArrayToHex(bytes);
	}

	/**
	 * Byte array to hex.
	 * 
	 * @param bytes
	 *            the bytes
	 * 
	 * @return the string
	 */
	private static String byteArrayToHex(byte[] bytes) {
		String hex = "";
		String cvt = "";
		for (int i = 0; i < bytes.length; i++) {
			cvt = Integer.toHexString(bytes[i] & 0XFF);
			if (cvt.length() == 1) {
				hex = hex + "0" + cvt;
			} else {
				hex = hex + cvt;
			}
		}
		return hex.toUpperCase();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) throws Exception{
		System.out.println(toMD5("陈良伟"));
		
	}
}
