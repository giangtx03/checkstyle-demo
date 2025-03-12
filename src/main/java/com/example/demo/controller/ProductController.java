package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductSerivce productSerivce;

    @PostMapping
    public Product createProduct(Product product){
        return productSerivce.save(product);
    }

    @GetMapping
    public List<Product> getAllProductByName(String name){
        return productSerivce.findByName(name);
    }
}
