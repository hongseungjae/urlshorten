package com.hong.urlshorten.service;


import com.hong.urlshorten.domain.Url;
import com.hong.urlshorten.domain.UrlConvert;
import com.hong.urlshorten.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private final UrlConvert urlConvert;

    public UrlService(UrlRepository urlRepository, UrlConvert urlConvert) {
        this.urlRepository = urlRepository;
        this.urlConvert = urlConvert;
    }


    public String Redirect(String path){

        Url findUrl = findUrl(path);
        findUrl.requestCountPlus();

        return findUrl.getOriginUrl();
    }

    public int getRequestCount(String path){
        return findUrl(path).getRequestCount();
    }


    public Url findUrl(String path) {
       String urlKey = urlConvert.getUrlKey(path);
       Optional url = urlRepository.findByUrl(urlKey);

        if(url.isPresent()){
            return (Url) url.get();
        }else{
            throw new IllegalStateException("존재하지 않는 URL 입니다.");
        }
        
        
    }

}
