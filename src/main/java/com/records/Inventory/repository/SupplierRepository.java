package com.records.Inventory.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.records.Inventory.dto.InventoryDto;
import com.records.Inventory.dto.SupplierDto;
import com.records.Inventory.entity.SupplierDetails;
import com.records.Inventory.entity.WarehouseDetails;

@Repository
public interface SupplierRepository extends CrudRepository<SupplierDetails, Integer> {
    // Additional query methods (if any) can be defined here
	Optional<SupplierDetails> findBySupplierId(Integer supplierId);

	InventoryDto save(SupplierDto dto);
}
