package com.hong.urlshorten.domain;

public class UrlCountForm {
    int requestCount;

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public UrlCountForm(int requestCount) {
        this.requestCount = requestCount;
    }
}
