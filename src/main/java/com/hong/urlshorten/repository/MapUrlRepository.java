package com.hong.urlshorten.repository;

import com.hong.urlshorten.domain.Url;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class MapUrlRepository implements UrlRepository{
    private final Map<String, Url> store  = new ConcurrentHashMap<>();
    @Override
    public void save(Url url) {
        store.put(url.getShortenUrl(), url);
        log.info("value={}", url.getShortenUrl());

    }

    @Override
    public Optional<Url> findByUrl(String decodedUrl) {
        log.info("decodedUrl={}", decodedUrl);
        return Optional.ofNullable(store.get(decodedUrl));
    }




}
