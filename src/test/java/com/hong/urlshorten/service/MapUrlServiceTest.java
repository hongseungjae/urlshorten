package com.hong.urlshorten.service;

import com.hong.urlshorten.domain.RandomUrlConvert;
import com.hong.urlshorten.domain.Url;
import com.hong.urlshorten.domain.UrlConvert;
import com.hong.urlshorten.repository.MapUrlRepository;
import com.hong.urlshorten.repository.UrlRepository;
import com.hong.urlshorten.utill.Base62;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MapUrlServiceTest {

    private UrlRepository repository = new MapUrlRepository();
    private final UrlConvert urlConvert = new RandomUrlConvert(new Base62());
    private final UrlService urlService = new UrlService(repository,urlConvert);

    @AfterEach
    public void init(){
        repository = new MapUrlRepository();
    }

    @Test
    public void findByUrlO(){
        //given
        Url url1 = new Url("https://www.naver.com","ASDFWE");
        repository.save(url1);

        //when
        Url url = urlService.findUrl("ASDFWE");

        //then
        Assertions.assertEquals(url1.getOriginUrl(), url.getOriginUrl());

    }

    @Test
    public void findByUrlX(){
        //given
        Url url1 = new Url("https://www.naver.com","ASDFWE");
        repository.save(url1);

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> urlService.findUrl("XXX"));

        //then
        Assertions.assertEquals(e.getMessage(),"존재하지 않는 URL 입니다.");
    }




}