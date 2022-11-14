package com.hong.urlshorten.service;

import com.hong.urlshorten.repositories.IStorage;
import com.hong.urlshorten.utill.Base62;
import javax.servlet.http.HttpServletRequest;


public class EncodeUrl {

    public String urlToShortenUrl(String originUrl, Base62 base62, IStorage storage){

        String index = storage.size()+"";
        byte[] Encoded = base62.encode(index.getBytes());
        String indexEncoded = new String(Encoded);
        return indexEncoded;

    }
    public String createUrl(HttpServletRequest request,String indexEncoded){
        String shortenUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"/"+indexEncoded+"/");
        return shortenUrl;
    }

}
