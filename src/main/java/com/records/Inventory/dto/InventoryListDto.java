package com.records.Inventory.dto;

import java.util.List;

import com.records.Inventory.entity.InventoryDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryListDto {
    private List<InventoryDto> inventories;
}

