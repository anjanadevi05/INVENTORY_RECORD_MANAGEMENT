package com.records.Inventory.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductListDto {
    private List<ProductDto> products;
}
