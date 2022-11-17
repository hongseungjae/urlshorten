package com.hong.urlshorten.domain;

import com.hong.urlshorten.utill.Base62;

import java.util.Random;


public class RandomUrlConvert implements UrlConvert {
    private final Base62 base62;

    public RandomUrlConvert(Base62 base62) {
        this.base62 = base62;
    }

    private final Random random = new Random();
    @Override
    public String UrlConvert(String originUrl) {
        String UrlConverted = base62.encoding(getRandomNumber());
        return UrlConverted;
    }

    public Long getRandomNumber(){
        return random.nextLong(Long.MAX_VALUE);
    }

    @Override
    public String getUrlKey(String value) {
        return value;
    }
}
