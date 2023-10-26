package com.example.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encrypt(String input) {
        try {
            // 创建MD5摘要算法实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入数据转换为字节数组
            byte[] inputData = input.getBytes();

            // 计算摘要
            byte[] mdBytes = md.digest(inputData);

            // 将摘要转换为十六进制字符串
            BigInteger bigInt = new BigInteger(1, mdBytes);
            String md5Str = bigInt.toString(16);

            // 补全前导零，确保输出为32位字符串
            while (md5Str.length() < 32) {
                md5Str = "0" + md5Str;
            }

            // 取截取部分作为18位乱码密码
            return md5Str.substring(8, 26);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String input = "1234";  // 要加密的初始数据
        String encrypted = encrypt(input);

        System.out.println("初始数据: " + input);
        System.out.println("加密结果: " + encrypted);
    }
}