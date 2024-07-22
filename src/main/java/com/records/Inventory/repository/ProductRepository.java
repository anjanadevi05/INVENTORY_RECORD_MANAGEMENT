package com.records.Inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.records.Inventory.dto.InventoryDto;
import com.records.Inventory.dto.ProductDto;
import com.records.Inventory.entity.ProductDetails;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails,Integer > {
    // Custom query to find a product by its product ID
    Optional<ProductDetails> findById(Integer productId);

	InventoryDto save(ProductDto dto);
}
