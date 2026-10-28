package com.giuli.tienda.service;

import com.giuli.tienda.dto.ProductDto;
import com.giuli.tienda.entity.Product;
import com.giuli.tienda.exception.ProductNotFoundException;
import com.giuli.tienda.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProduct(long id) {
        Product foundProduct = productRepository.findById(id).orElse(null);

        if (foundProduct == null) {
            throw new ProductNotFoundException("Product not found with that id");
        }

        return foundProduct;
    }

    public Product createProduct(ProductDto dto) {
        Product product = new Product(dto.name(), dto.price());
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    public Product updateProduct(long id, ProductDto dto) {
        Product foundProduct = productRepository.findById(id).orElse(null);

        if (foundProduct == null) {
            throw new ProductNotFoundException("Product not found for update");
        }

        foundProduct.setName(dto.name());
        foundProduct.setPrice(dto.price());

        return productRepository.save(foundProduct);
    }

    public void deleteProduct(long id) {
        Product foundProduct = productRepository.findById(id).orElse(null);

        if (foundProduct == null) {
            throw new ProductNotFoundException("Product not found for deletion");
        }

        productRepository.delete(foundProduct);
    }
}
