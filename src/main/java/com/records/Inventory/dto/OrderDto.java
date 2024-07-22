package com.records.Inventory.dto;

import java.time.LocalDate;
import java.util.Date;
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
public class OrderDto {
    private Integer id; // Unique identifier for the order
    private Integer orderId;
    private LocalDate orderDate; // Date when the order was placed
    private Integer quantity; // Quantity of products in the order
    private Double totalPrice; // Total price of the order
    private ProductDto product; // Product details related to the order
    private SupplierDto supplier; // Supplier details related to the order
}
