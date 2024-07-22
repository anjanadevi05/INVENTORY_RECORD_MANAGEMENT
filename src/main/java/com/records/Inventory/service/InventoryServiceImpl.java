package com.records.Inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.records.Inventory.controller.InventoryController;
import com.records.Inventory.dto.*;
import com.records.Inventory.entity.*;
import com.records.Inventory.repository.*;
import com.records.Inventory.util.InventoryUtil;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.records.Inventory.exception.InventoryException;
import com.records.Inventory.map.EntityMapper;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;
    
    @Autowired
    private EntityMapper productMapper;
    
  

    @Override
    public Integer saveProductInfo(ProductDto productDto) {
        ProductDetails product = productMapper.mapToBasicProductEntity(productDto);
        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public Integer saveInventoryInfo(InventoryDto dto) {
    	log.info("InventoryServiceImpl :: saveInventoryInfo");

        if (dto.getProduct() == null || dto.getProduct().getId() == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
        if (dto.getWarehouse() == null || dto.getWarehouse().getId() == null) {
            throw new IllegalArgumentException("Warehouse ID must not be null");
        }

        InventoryDetails inventory = productMapper.mapToInventoryEntity(dto);
        inventory = inventoryRepository.save(inventory);
        return inventory.getId();
    }

    @Transactional
    @Override
    public Integer saveOrderInfo(OrderDto dto) {
        // Validate the OrderDto
        if (dto == null || dto.getProduct() == null || dto.getSupplier() == null) {
            throw new IllegalArgumentException("OrderDto or its related entities are null");
        }

        OrderDetails order = productMapper.mapToOrderEntity(dto);
        OrderDetails savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @Override
    public Integer saveSupplierInfo(SupplierDto dto) {
        SupplierDetails supplier = new SupplierDetails();
        supplier.setSupplierId(dto.getSupplierId());
        supplier.setName(dto.getName());
        supplier.setContactInformation(dto.getContactInformation());

        List<ProductDetails> products = dto.getProducts().stream()
                .map(p -> productRepository.findById(p.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + p.getProductId())))
                .collect(Collectors.toList());
        supplier.setProducts(products);

        SupplierDetails savedSupplier = supplierRepository.save(supplier);
        return savedSupplier.getSupplierId();
    }


    @Override
    public Integer saveWarehouseInfo(WarehouseDto dto) {
        WarehouseDetails warehouse = InventoryUtil.dtoToEntity(dto);
        WarehouseDetails savedWarehouse = warehouseRepository.save(warehouse);
        return savedWarehouse.getHouseId();
    }

    @Override
    public ProductDto getProductBasicInfo(Integer productId) {
        Optional<ProductDetails> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            ProductDetails product = productOptional.get();
            return InventoryUtil.entityToDto(product);
        } else {
            throw new InventoryException("Product not found with ID: " + productId);
        }
    }

    @Override
    public ProductWithDetailsDto getProductDetailInfo(Integer productId) {
        ProductDetails product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.mapToProductWithDetailsDto(product);
    }


    @Override
    public InventoryDto getInventoryInfo(Integer id) {
        Optional<InventoryDetails> inventOptional = inventoryRepository.findByInventId(id);

        if (inventOptional.isPresent()) {
            InventoryDetails inventory = inventOptional.get();
            return InventoryUtil.entityToDto(inventory);
        } else {
            throw new InventoryException("Inventory not found with ID: " + id);
        }
    }

    @Override
    public WarehouseDto getWarehouseInfo(Integer id) {
        Optional<WarehouseDetails> warehouseOptional = warehouseRepository.findByHouseId(id);

        if (warehouseOptional.isPresent()) {
            WarehouseDetails warehouse = warehouseOptional.get();
            return InventoryUtil.entityToDto(warehouse);
        } else {
            throw new InventoryException("Warehouse not found with ID: " + id);
        }
    }

    @Override
    public SupplierDto getSupplierInfo(Integer id) {
        Optional<SupplierDetails> supplierOptional = supplierRepository.findBySupplierId(id);

        if (supplierOptional.isPresent()) {
            SupplierDetails supplier = supplierOptional.get();
            return InventoryUtil.entityToDto(supplier);
        } else {
            throw new InventoryException("Supplier not found with ID: " + id);
        }
    }

    @Override
    public OrderDto getOrderInfo(Integer id) {
        Optional<OrderDetails> orderOptional = orderRepository.findByOrderId(id);

        if (orderOptional.isPresent()) {
            OrderDetails order = orderOptional.get();
            return InventoryUtil.entityToDto(order);
        } else {
            throw new InventoryException("Order not found with ID: " + id);
        }
    }

    @Override
    public Integer updateInventoryInfo(InventoryDto dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Invalid InventoryDto or ID");
        }

        Optional<InventoryDetails> optionalInventory = inventoryRepository.findById(dto.getId());
        if (!optionalInventory.isPresent()) {
            throw new EntityNotFoundException("Inventory item not found");
        }

        InventoryDetails inventory = optionalInventory.get();
        inventory.setInventId(dto.getInventId());
        inventory.setQuantity(dto.getQuantity());
        // Update the product and warehouse if necessary
        // inventory.setProduct(mapProductDtoToEntity(dto.getProduct()));
        // inventory.setWarehouse(mapWarehouseDtoToEntity(dto.getWarehouse()));

        InventoryDetails updatedInventory = inventoryRepository.save(inventory);
        return updatedInventory.getInventId();
    }

    @Override
    public Integer updateProductInfo(ProductDto dto) {
        ProductDetails product = productMapper.mapToBasicProductEntity(dto);
        ProductDetails updatedProduct = productRepository.save(product);
        return updatedProduct.getProductId();
    }

    @Override
    public Integer updateWarehouseInfo(WarehouseDto dto) {
        WarehouseDetails warehouse = InventoryUtil.dtoToEntity(dto);
        WarehouseDetails updatedWarehouse = warehouseRepository.save(warehouse);
        return updatedWarehouse.getHouseId();
    }

    @Override
    public Integer updateSupplierInfo(SupplierDto dto) {
        SupplierDetails supplier = InventoryUtil.dtoToEntity(dto);
        SupplierDetails updatedSupplier = supplierRepository.save(supplier);
        return updatedSupplier.getSupplierId();
    }

    @Override
    @Transactional
    public Integer updateOrderInfo(OrderDto dto) {
        if (dto == null || dto.getOrderId() == null) {
            throw new IllegalArgumentException("Invalid OrderDto or ID");
        }

        OrderDetails existingOrder = orderRepository.findByOrderId(dto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + dto.getOrderId()));

        // Update the existing order with values from the DTO
        existingOrder.setQuantity(dto.getQuantity());
        // Update other fields as necessary
        // Example: existingOrder.setSomeField(dto.getSomeField());

        OrderDetails updatedOrder = orderRepository.save(existingOrder);
        return updatedOrder.getId();
    }

}