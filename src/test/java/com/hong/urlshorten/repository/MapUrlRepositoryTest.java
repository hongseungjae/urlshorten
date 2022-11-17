package com.hong.urlshorten.repository;

import com.hong.urlshorten.domain.Url;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapUrlRepositoryTest {


    UrlRepository<Url> repository = new MapUrlRepository();


    @Test
    public void savefind(){

        Url url1 = new Url("https://www.naver.com","ASDFWE");
        Url url2 = new Url("https://github.com","SDFD");
        repository.save(url1);
        repository.save(url2);

        Assertions.assertEquals(repository.findByUrl("ASDFWE").get().getOriginUrl(),"https://www.naver.com");
        Assertions.assertEquals(repository.findByUrl("SDFD").get().getOriginUrl(),"https://github.com");

    }


}