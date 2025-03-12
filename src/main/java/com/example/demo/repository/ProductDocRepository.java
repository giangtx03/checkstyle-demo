package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductDocRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findByName(String name);
}
