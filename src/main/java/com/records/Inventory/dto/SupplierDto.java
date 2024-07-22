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
public class SupplierDto {
	private Integer id; 
	private Integer supplierId;// Unique identifier for the supplier
    private String name; // Name of the supplier
    private String location; // Address or location of the supplier
    private String contactInformation; // Contact details for the supplier

    private List<ProductDto> products; // List of products supplied by this supplier

}
