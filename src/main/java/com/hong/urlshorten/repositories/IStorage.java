package com.hong.urlshorten.repositories;

public interface IStorage <T> {
    void add(T value);
    T get(int index);
    int size();;

}
