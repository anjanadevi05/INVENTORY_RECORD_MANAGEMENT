package com.records.Inventory.dto;

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
public class InventoryDto {
	    private Integer id;
	    private Integer inventId;
	    private Integer quantity;
	    private WarehouseDto warehouse;
	    private ProductDto product;
}
