package com.records.Inventory.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseListDto {
	private List<WarehouseDto> warehouses;

}
