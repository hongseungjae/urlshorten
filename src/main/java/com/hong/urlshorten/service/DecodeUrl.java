package com.hong.urlshorten.service;
import com.hong.urlshorten.utill.Base62;


public class DecodeUrl {

    public String urlToIndex(String originUrl, Base62 base62){

        byte[] decoded = base62.decode(originUrl.getBytes());
        String indexDecoded = new String(decoded);
        return indexDecoded;
    }
}
