package com.giuli.tienda.controller;

import com.giuli.tienda.dto.ProductDto;
import com.giuli.tienda.entity.Product;
import com.giuli.tienda.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.findProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody ProductDto dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id")long id) {
        productService.deleteProduct(id);
    }

}
