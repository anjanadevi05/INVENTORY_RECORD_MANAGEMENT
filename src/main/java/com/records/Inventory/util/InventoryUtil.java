package com.records.Inventory.util;

import java.util.List;
import java.util.stream.Collectors;

import com.records.Inventory.dto.InventoryDto;
import com.records.Inventory.dto.OrderDto;
import com.records.Inventory.dto.OrderListDto;
import com.records.Inventory.dto.ProductDto;
import com.records.Inventory.dto.SupplierDto;
import com.records.Inventory.dto.SupplierListDto;
import com.records.Inventory.dto.WarehouseDto;
import com.records.Inventory.entity.InventoryDetails;
import com.records.Inventory.entity.OrderDetails;
import com.records.Inventory.entity.ProductDetails;
import com.records.Inventory.entity.SupplierDetails;
import com.records.Inventory.entity.WarehouseDetails;

public class InventoryUtil {

    private InventoryUtil() {
    }

    // Convert ProductDto to ProductDetails
    public static ProductDetails dtoToEntity(ProductDto dto) {
        return ProductDetails.builder()
                .productId(dto.getProductId())
                .prodName(dto.getProdName())
                .prodCategory(dto.getProdCategory())
                .prodPrice(dto.getProdPrice())
                .build();
    }

    public static InventoryDetails dtoToEntity(InventoryDto dto) {
        if (dto == null) {
            return null;
        }

        InventoryDetails entity = new InventoryDetails();
        entity.setId(dto.getId());
        entity.setInventId(dto.getInventId());
        entity.setQuantity(dto.getQuantity());

        // Handle null values for warehouse and product
        if (dto.getWarehouse() != null) {
            entity.setWarehouse(dtoToEntity(dto.getWarehouse()));
        } else {
            entity.setWarehouse(null);
        }

        if (dto.getProduct() != null) {
            entity.setProduct(dtoToEntity(dto.getProduct()));
        } else {
            entity.setProduct(null);
        }

        return entity;
    }

    // Convert WarehouseDto to WarehouseDetails
    public static WarehouseDetails dtoToEntity(WarehouseDto dto) {
        return WarehouseDetails.builder()
                .id(dto.getId())
                .houseId(dto.getHouseId())
                .name(dto.getName())
                .location(dto.getLocation())
                .contact(dto.getContact())
                .build();
    }

    // Convert List of SupplierDto to List of SupplierDetails
    public static List<SupplierDetails> dtoToEntities(SupplierListDto dto) {
        return dto.getSuppliers().stream().map(InventoryUtil::dtoToEntity).collect(Collectors.toList());
    }

    // Convert List of OrderDto to List of OrderDetails
    public static List<OrderDetails> dtoToEntities(OrderListDto dto) {
        return dto.getOrders().stream().map(InventoryUtil::dtoToEntity).collect(Collectors.toList());
    }

    // Convert SupplierDto to SupplierDetails
    public static SupplierDetails dtoToEntity(SupplierDto dto) {
        return SupplierDetails.builder()
                .supplierId(dto.getSupplierId())
                .name(dto.getName())
                .location(dto.getLocation())
                .contactInformation(dto.getContactInformation())
                .build();
    }

    // Convert OrderDto to OrderDetails
    public static OrderDetails dtoToEntity(OrderDto dto) {
        return OrderDetails.builder()
                .orderId(dto.getOrderId())
                .orderDate(dto.getOrderDate())
                .quantity(dto.getQuantity())
                .totalPrice(dto.getTotalPrice())
                .product(dtoToEntity(dto.getProduct())) // Convert ProductDto to ProductDetails
                .supplier(dtoToEntity(dto.getSupplier())) // Convert SupplierDto to SupplierDetails
                .build();
    }

    // Convert ProductDetails to ProductDto
    public static ProductDto entityToDto(ProductDetails entity) {
        return ProductDto.builder()
                .productId(entity.getProductId())
                .prodName(entity.getProdName())
                .prodCategory(entity.getProdCategory())
                .prodPrice(entity.getProdPrice())
                .build();
    }

    // Convert InventoryDetails to InventoryDto
    public static InventoryDto entityToDto(InventoryDetails entity) {
        return InventoryDto.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .warehouse(entityToDto(entity.getWarehouse())) // Convert WarehouseDetails to WarehouseDto
                .product(entityToDto(entity.getProduct())) // Convert ProductDetails to ProductDto
                .build();
    }

    // Convert WarehouseDetails to WarehouseDto
    public static WarehouseDto entityToDto(WarehouseDetails entity) {
        return WarehouseDto.builder()
                .id(entity.getId())
                .houseId(entity.getHouseId())
                .name(entity.getName())
                .location(entity.getLocation())
                .contact(entity.getContact())
                .build();
    }

    // Convert SupplierDetails to SupplierDto
    public static SupplierDto entityToDto(SupplierDetails entity) {
        return SupplierDto.builder()
                .supplierId(entity.getSupplierId())
                .name(entity.getName())
                .location(entity.getLocation())
                .contactInformation(entity.getContactInformation())
                .build();
    }

    // Convert OrderDetails to OrderDto
    public static OrderDto entityToDto(OrderDetails entity) {
        return OrderDto.builder()
                .orderId(entity.getOrderId())
                .orderDate(entity.getOrderDate())
                .quantity(entity.getQuantity())
                .totalPrice(entity.getTotalPrice())
                .product(entityToDto(entity.getProduct())) // Convert ProductDetails to ProductDto
                .supplier(entityToDto(entity.getSupplier())) // Convert SupplierDetails to SupplierDto
                .build();
    }
}
