package com.hong.urlshorten.repository;

import com.hong.urlshorten.domain.Url;
import java.util.ArrayList;
import java.util.Optional;

public class ListIndexUrlRepository implements UrlRepository{

    private final ArrayList<Url> arr = new ArrayList<Url>();

    @Override
    public void save(Url url) {
        arr.add(url);
    }

    @Override
    public Optional<Url> findByUrl(String decodedUrl) {
        integerCheck(decodedUrl);
        indexRangeCheck(decodedUrl);
        Url findUrl = arr.get(Integer.parseInt(decodedUrl+""));
        return Optional.ofNullable(findUrl);
    }

    public void indexRangeCheck(String indexDecoded) {
        long index = Long.parseLong(indexDecoded);
        if ( 0 <= index && index < arr.size()) {
        }else{
            throw new IllegalStateException("매칭되지 않은 URL 입니다.");
        }

    }

    public void integerCheck(String indexDecoded) {

        if ((indexDecoded).matches("-?\\d+")) {
        }else{
            throw new IllegalStateException("매칭되지 않은 URL 입니다.");
        }

    }


}
