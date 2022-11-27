package com.hong.urlshorten.repository;

import com.hong.urlshorten.domain.Url;

import java.util.Optional;

public interface UrlRepository<T> {
    void save(Url value);
    Optional<T> findByUrl(String index);
}
