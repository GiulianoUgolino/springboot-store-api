package com.giuli.tienda.repository;

import com.giuli.tienda.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
