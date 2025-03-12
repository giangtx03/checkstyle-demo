package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSerivce {

    private final ProductDocRepository productDocRepository;

    public Product save(Product product){
        return productDocRepository.save(product);
    }

    public List<Product> findByName(String name){
        return productDocRepository.findByName(name);
    }
}
