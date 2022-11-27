package com.hong.urlshorten.domain;

public class Url {
    private Long Id;
    private String originUrl;
    private String shortenUrl;

    int requestCount = 0;

    public Url(String origin, String shorten){
        this.originUrl = origin;
        this.shortenUrl = shorten;
    }

    public void requestCountPlus(){
        requestCount++;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOriginUrl() {
        return originUrl;
    }


    public String getShortenUrl() {
        return shortenUrl;
    }

    public int getRequestCount() {
        return requestCount;
    }

}
