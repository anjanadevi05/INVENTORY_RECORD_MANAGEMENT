package com.records.Inventory.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.records.Inventory.dto.InventoryDto;
import com.records.Inventory.dto.OrderDto;
import com.records.Inventory.entity.OrderDetails;

@Repository
public interface OrderRepository extends CrudRepository<OrderDetails, Integer> {
    // Define additional query methods if needed
	Optional<OrderDetails> findByOrderId(Integer orderId);

}