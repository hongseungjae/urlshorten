package com.hong.urlshorten.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UrlDTO {


    String originUrl;
    String shortenUrl;

    int requestCount = 1;

    public UrlDTO(String origin, String shorten){
        this.originUrl = origin+"";
        this.shortenUrl = shorten;
    }

}
