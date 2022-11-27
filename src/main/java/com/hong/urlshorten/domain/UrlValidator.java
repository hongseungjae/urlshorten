package com.hong.urlshorten.domain;

import org.springframework.stereotype.Component;

@Component
public class UrlValidator {


    public void urlValidate(String url){

        if(url.length() > 8){
        }else throw new IllegalStateException("형식에 맞지 않는 URL 입니다.");

        if(url.substring(0,8).equals("https://")){
        }else throw new IllegalStateException("형식에 맞지 않는 URL 입니다.");

    }



}
