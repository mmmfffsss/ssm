package com.deyuan.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 孟哥
 * <p>
 * 2020/10/31
 */
public class BCryptPasswordEncoderUtil {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);

    }

    public static void main(String[] args) {
        String s = BCryptPasswordEncoderUtil.encodePassword("cctv1");
        System.out.println(s);
    }
}
