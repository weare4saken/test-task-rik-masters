package com.wea4saken.rikmasters.service;

import java.util.List;

public interface CRUDService<T, R> {
    T add(T item);
    T update(R id, T item);
    List<T> getAll();
    T get(R id);
    void delete(R id);
    boolean validCheck(T item);
}