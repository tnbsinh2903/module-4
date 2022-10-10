package com.cg.service;

import java.util.Optional;

public interface IGeneralService<T> {

    Iterable<T> findAll();
    Optional<T> findId (Long id);

    T save(T t);

    void remove(Long id);
}
