package com.records.Inventory.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.records.Inventory.entity.InventoryDetails;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryDetails, Integer> {
    Optional<InventoryDetails> findByInventId(Integer inventId);
}