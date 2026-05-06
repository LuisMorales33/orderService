package com.practice.orderService.utils;

public class Utils {
    /**
     * Converts a string to uppercase, handling null values gracefully.
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        return str != null ? str.toUpperCase() : null;
    }


}
