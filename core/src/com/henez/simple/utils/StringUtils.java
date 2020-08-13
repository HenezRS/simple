package com.henez.simple.utils;

public class StringUtils {
    private StringUtils() {

    }

    public static String toHex(int value) {
        String string = Integer.toHexString(value);
        return string.length() == 1 ? "0" + string : string;
    }

    public static int fromHex(String hex) {
        return Integer.parseInt(hex, 16);
    }
}
