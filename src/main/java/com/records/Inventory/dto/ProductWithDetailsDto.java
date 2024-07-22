package com.records.Inventory.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithDetailsDto {
	    private Integer id;
	    private Integer productId;
	    private String prodName;
	    private String prodCategory;
	    private Integer prodPrice;
	    private List<InventoryDto> inventories;
	    private List<OrderDto> orders;
	    private List<SupplierDto> suppliers;
	    private List<WarehouseDto> warehouses;
	    // Getters and Setters
	}


