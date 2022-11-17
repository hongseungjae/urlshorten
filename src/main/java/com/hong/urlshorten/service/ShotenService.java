package com.hong.urlshorten.service;

import com.hong.urlshorten.domain.Url;
import com.hong.urlshorten.domain.UrlConvert;
import com.hong.urlshorten.domain.UrlValidator;
import com.hong.urlshorten.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShotenService {

    private final UrlRepository urlRepository;
    private final UrlValidator urlValidator;
    private final UrlConvert urlConvert;

    public ShotenService(UrlRepository urlRepository, UrlValidator urlValidator, UrlConvert urlConvert) {
        this.urlRepository = urlRepository;
        this.urlValidator = urlValidator;
        this.urlConvert = urlConvert;
    }


    public String shorten(String originUrl){

        urlValidator.urlValidate(originUrl);

        String shotenUrl = urlConvert.UrlConvert(originUrl);
        urlRepository.save(new Url(originUrl, shotenUrl));

        log.info("OriginUrl={}, shotenUrl={}", originUrl, shotenUrl);

        return shotenUrl;
    }



}
