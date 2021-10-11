package com.example.finaltsest.utils;

public class CommonUtils {
    public static int string2int(String string, int defaultVal) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultVal;
    }

    public static double string2float(String string, double defaultVal) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultVal;
    }
}
