package com.hong.urlshorten;


import com.hong.urlshorten.domain.RandomUrlConvert;
import com.hong.urlshorten.domain.SequenceUrlConvert;
import com.hong.urlshorten.domain.UrlConvert;
import com.hong.urlshorten.repository.ListUrlRepository;
import com.hong.urlshorten.repository.MapUrlRepository;
import com.hong.urlshorten.repository.UrlRepository;
import com.hong.urlshorten.utill.Base62;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UrlRepository urlRepository(){
        return new MapUrlRepository();
    }

    @Bean
    public UrlConvert urlConvert(){
        return new RandomUrlConvert(base62());
    }

    @Bean
    public Base62 base62(){
        return new Base62();
    }

}
