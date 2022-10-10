package com.cg.service;

import com.cg.model.Product;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IProductService   {

    Optional<Product> findById(Long id);

    Iterable<Product> findAll();

}
