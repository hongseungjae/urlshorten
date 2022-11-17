package com.hong.urlshorten.service;

import com.hong.urlshorten.domain.RandomUrlConvert;
import com.hong.urlshorten.domain.Url;
import com.hong.urlshorten.domain.UrlConvert;
import com.hong.urlshorten.domain.UrlValidator;
import com.hong.urlshorten.repository.ListUrlRepository;
import com.hong.urlshorten.repository.MapUrlRepository;
import com.hong.urlshorten.repository.UrlRepository;
import com.hong.urlshorten.utill.Base62;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MapShotenServiceTest {

    private final UrlRepository urlRepository = new MapUrlRepository();
    private final Base62 base62 = new Base62();
    private final UrlValidator urlValidator = new UrlValidator();
    private final UrlConvert urlConvert = new RandomUrlConvert(base62);
    private final ShotenService shotenService = new ShotenService(urlRepository,urlValidator, urlConvert);

    @Test
    public void serviceO(){

        //given
        String shorten = shotenService.shorten("https://www.naver.com/asd");

        //when
        Url url = (Url) urlRepository.findByUrl(shorten).get();

        Assertions.assertEquals("https://www.naver.com/asd",url.getOriginUrl());

        //then

    }


    @Test
    public void serviceX(){

        //given
        //when

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> shotenService.shorten("https:/www.naver.com/af"));
        Assertions.assertEquals(e.getMessage(),"형식에 맞지 않는 URL 입니다.");


        IllegalStateException e1 = assertThrows(IllegalStateException.class, () -> shotenService.shorten("htps:/www.naver.com/af"));
        Assertions.assertEquals(e1.getMessage(),"형식에 맞지 않는 URL 입니다.");
        //then

    }


}