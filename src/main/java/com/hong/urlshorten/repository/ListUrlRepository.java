package com.hong.urlshorten.repository;

import com.hong.urlshorten.domain.Url;
import java.util.ArrayList;
import java.util.Optional;

public class ListUrlRepository implements UrlRepository{

    private final ArrayList<Url> store = new ArrayList<>();

    @Override
    public void save(Url url) {
        store.add(url);
    }

    @Override
    public Optional<Url> findByUrl(String decodedUrl) {
        return store.stream()
                .filter(url -> url.getShortenUrl().equals(decodedUrl))
                .findAny();
    }




}
