package com.hai.util;

import java.security.MessageDigest;

/**
 * Created by chenz at 7:38 on 2021/2/28
 */
public class MD5Tool {
    private static final String privateKey = "czh202012121857";

    public static String getMD5(String s) {
        s += privateKey;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes("UTF-8");
            //System.out.println(Arrays.toString(btInput));
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            //System.out.println(Arrays.toString(md));
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return s;
        }
    }
}
