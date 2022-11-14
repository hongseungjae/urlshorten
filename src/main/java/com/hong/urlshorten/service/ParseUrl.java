package com.hong.urlshorten.service;

import lombok.Getter;
import lombok.Setter;
import javax.servlet.http.HttpServletRequest;

@Setter @Getter
public class ParseUrl {

    String url;

    HttpServletRequest request;

    public ParseUrl(HttpServletRequest originRequest){
        this.request = originRequest;
    }

    public void pathExtract(){
        url = request.getRequestURI();
    }
    public void pathSplit() {
        url = url.substring(1,url.length()-1);
    }

    public void pathCountSplit() {
        String[] strarr = url.split("/");
        String str = strarr[1];
        url = str;
    }




}
