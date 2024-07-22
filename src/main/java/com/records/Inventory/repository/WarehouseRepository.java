package com.records.Inventory.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.records.Inventory.dto.InventoryDto;
import com.records.Inventory.dto.WarehouseDto;
import com.records.Inventory.entity.ProductDetails;
import com.records.Inventory.entity.WarehouseDetails;

@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseDetails, Integer> {
    // Additional query methods (if any) can be defined here
	Optional<WarehouseDetails> findByHouseId(Integer houseId);

	InventoryDto save(WarehouseDto dto);
}
