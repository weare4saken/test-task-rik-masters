package com.wea4saken.rikmasters.service;

public interface FindService<T, R>{

    T findById(R id);

}