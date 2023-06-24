package com.wea4saken.rikmasters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<T, R> {
    T add(T item);
    T update(R id, T item);
    List<T> getAll(Integer pageNumber, Integer pageSize);
    T get(R id);
    void delete(R id);
    boolean validCheck(T item);
}