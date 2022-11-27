package com.hong.urlshorten.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UrlValidatorTest {

    UrlValidator urlValidator = new UrlValidator();



    @Test
    void url(){
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> urlValidator.urlValidate("httpswww.naver.com"));
        Assertions.assertEquals(e.getMessage(),"형식에 맞지 않는 URL 입니다.");
    }

    @Test
    void url1(){
        IllegalStateException e1 = assertThrows(IllegalStateException.class, () -> urlValidator.urlValidate("http"));
        Assertions.assertEquals(e1.getMessage(),"형식에 맞지 않는 URL 입니다.");
    }

    @Test
    void url2(){
        urlValidator.urlValidate("https://www.naver.com"); // 정상
    }


}