package com.example.demo.service;


import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product create(Product product){
        return productRepository.save(new Product(product.getName(), product.getDescription(), product.getPrice()));
    }

    public boolean update(Long id, Product productData){
        Optional<Product> maybeProduct = productRepository.findById(id);

        if (maybeProduct.isPresent()){
            Product product = maybeProduct.get();
            product.setName(productData.getName());
            product.setDescription(productData.getDescription());
            product.setPrice(productData.getPrice());

            productRepository.save(product);

            return true;
        }else
            return false;

    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
