package com.cg.service;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGeneralService<T> {

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
