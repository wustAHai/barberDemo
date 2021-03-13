package com.hai.util;

/**
 * Created by chenz on 2020/12/22 19:52
 */
public class CheckStrings {
    public static boolean isValid(String... params) {
        if (params == null || params.length == 0) {
            return false;
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null || params[i].trim().equals("")) {
                return false;
            }
        }
        return true;
    }
}
