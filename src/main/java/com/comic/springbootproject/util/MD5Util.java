package com.comic.springbootproject.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

public class MD5Util {
    private static final String SALT = "&%5123***&&%%$$#@";

    public static String getMD5(String password) {
        if (StringUtils.isBlank(password)) {
            return null;
        }
        String base = password + "/" + SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.getMD5("111111"));
    }
}
