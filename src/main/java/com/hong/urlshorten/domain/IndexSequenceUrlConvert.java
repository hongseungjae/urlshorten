package com.hong.urlshorten.domain;

import com.hong.urlshorten.utill.Base62;


public class IndexSequenceUrlConvert implements UrlConvert {
    private final Base62 base62;
    private long sequence = 0L;

    public IndexSequenceUrlConvert(Base62 base62) {
        this.base62 = base62;
    }

    @Override
    public String UrlConvert(String originUrl) {
        String UrlConverted = base62.encoding(sequence);
        sequence++;
        return UrlConverted;
    }

    @Override
    public String getUrlKey(String value) {
        Long urlEncoded = base62.decoding(value);
        return urlEncoded+"";
    }
}
