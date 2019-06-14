package com.cmdi.model.mro;

/**
 * @author: 高宗宝
 * @date: 2019/5/23
 * @version: 1.0
 * @description: someword
 */
public class MroUtil {
    public static String NIL = "NIL";
    public static Integer toInt(String in) {
        return (in == null || "".equals(in.trim()) || NIL.equalsIgnoreCase(in.trim())) ? -999 : Integer.parseInt(in.trim());
    }

    public static Long toLong(String in) {
        return (in == null || "".equals(in.trim()) || NIL.equalsIgnoreCase(in.trim())) ? -999 : Long.parseLong(in.trim());
    }
}
