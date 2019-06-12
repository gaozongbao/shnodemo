package com.cmdi.util;

public class ExcelColumnToIndex {
	public static void main(String[] args) {
		System.out.println(excelColStrToNum("AA"));
		System.out.println(excelColIndexToStr(1));
	}
	/**将字母列转换为数字，如A转换为1
	 * Excel column index begin 1
	 * */
	public static int excelColStrToNum(String colStr) {
        int num = 0;
        int result = 0;
        int length = colStr.length();
        for(int i = 0; i < length; i++) {
            char ch = colStr.charAt(length - i - 1);
            num = (int)(ch - 'A' + 1) ;
            num *= Math.pow(26, i);
            result += num;
        }
        return result;
    }
	
	/**将数字列转换为字母，如1转换为A
	 * Excel column index begin 1
	 * */
	 public static String excelColIndexToStr(int columnIndex) {
	        if (columnIndex <= 0) {
	            return null;
	        }
	        String columnStr = "";
	        columnIndex--;
	        do {
	            if (columnStr.length() > 0) {
	                columnIndex--;
	            }
	            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
	            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
	        } while (columnIndex > 0);
	        return columnStr;
	    }
}
