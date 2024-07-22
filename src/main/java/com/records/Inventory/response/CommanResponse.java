package com.records.Inventory.response;

import com.records.Inventory.dto.InventoryDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommanResponse<T> {
    private boolean isError;
    private T data;
    private String message;
}
