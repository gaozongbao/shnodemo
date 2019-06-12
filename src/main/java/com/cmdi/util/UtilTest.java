package com.cmdi.util;

public class UtilTest {
	public static void main(String[] args) throws Exception {
		XLSX2CSV xlsx2csv = new XLSX2CSV("E:\\testdata\\gongcan\\深圳_2017-11-15.xlsx","E:\\testdata\\gongcan\\aa.csv");
		xlsx2csv.process();
	}
	
}
