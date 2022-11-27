package com.hong.urlshorten.utill;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Base62 {

    private static final String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int BASE62 = 62;

    public String encoding(long param) {
        StringBuffer sb = new StringBuffer();
        while(param > 0) {
            sb.append(BASE62_CHAR.charAt((int) (param % BASE62)));
            param /= BASE62;
        }
        if(sb.length()==0) return "A";
        return sb.toString();
    }


    public long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE62_CHAR.indexOf(param.charAt(i)) * power;
            power *= BASE62;
        }
        return sum;
    }


}