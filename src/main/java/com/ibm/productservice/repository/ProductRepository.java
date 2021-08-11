package com.ibm.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.productservice.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByProductName(String name);
}
