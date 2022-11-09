package com.hong.urlshorten.repositories;
import java.util.ArrayList;


public class ListStorage<T> implements IStorage<T> {

    ArrayList<T> arr = new ArrayList<T>();

    @Override
    public void add(T value) {
        arr.add(value);
    }

    @Override
    public T get(int index) {
        return arr.get(index);
    }

    public int size(){
        return arr.size();
    }


}
