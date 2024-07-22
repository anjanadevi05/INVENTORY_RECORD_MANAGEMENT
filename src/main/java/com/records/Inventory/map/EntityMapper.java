package com.records.Inventory.map;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.records.Inventory.dto.*;
import com.records.Inventory.entity.*;
import com.records.Inventory.repository.ProductRepository;
import com.records.Inventory.repository.SupplierRepository;
import com.records.Inventory.repository.WarehouseRepository;

import jakarta.persistence.EntityNotFoundException;



@Component
public class EntityMapper {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;

    // Map to detailed Product entity
    public ProductDetails mapToProductEntity(ProductWithDetailsDto productDto) {
        if (productDto == null) {
            return null;
        }

        ProductDetails product = new ProductDetails();
        product.setId(productDto.getId());
        product.setProductId(productDto.getProductId()); // Add this line
        product.setProdName(productDto.getProdName());
        product.setProdCategory(productDto.getProdCategory());
        product.setProdPrice(productDto.getProdPrice());

        // Handle potential null lists
        List<InventoryDetails> inventories = (productDto.getInventories() != null) 
                ? productDto.getInventories().stream()
                    .map(this::mapToInventoryEntity)
                    .collect(Collectors.toList())
                : List.of(); // or new ArrayList<>()

        product.setInventories(inventories);

        List<OrderDetails> orders = (productDto.getOrders() != null) 
                ? productDto.getOrders().stream()
                    .map(this::mapToOrderEntity)
                    .collect(Collectors.toList())
                : List.of(); // or new ArrayList<>()

        product.setOrders(orders);

        List<SupplierDetails> suppliers = (productDto.getSuppliers() != null) 
                ? productDto.getSuppliers().stream()
                    .map(this::mapToSupplierEntity)
                    .collect(Collectors.toList())
                : List.of(); // or new ArrayList<>()

        product.setSuppliers(suppliers);

        List<WarehouseDetails> warehouses = (productDto.getWarehouses() != null) 
                ? productDto.getWarehouses().stream()
                    .map(this::mapToWarehouseEntity)
                    .collect(Collectors.toList())
                : List.of(); // or new ArrayList<>()

        product.setWarehouses(warehouses);

        return product;
    }

    // Map to basic Product entity
    public ProductDetails mapToBasicProductEntity(ProductDto productBasicDto) {
        if (productBasicDto == null) {
            return null;
        }

        ProductDetails product = new ProductDetails();
        product.setId(productBasicDto.getId());
        product.setProductId(productBasicDto.getProductId()); // Add this line
        product.setProdName(productBasicDto.getProdName());
        product.setProdCategory(productBasicDto.getProdCategory());
        product.setProdPrice(productBasicDto.getProdPrice());

        return product;
    }

    public InventoryDetails mapToInventoryEntity(InventoryDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("InventoryDto cannot be null");
        }

        InventoryDetails entity = InventoryDetails.builder()
                .Id(dto.getId())
                .inventId(dto.getInventId())
                .quantity(dto.getQuantity())
                .build();

        // Assuming you have methods to convert ProductDto to ProductDetails
        if (dto.getProduct() != null) {
            ProductDetails product = mapToBasicProductEntity(dto.getProduct());
            entity.setProduct(product);
        }

        // Assuming you have methods to convert WarehouseDto to WarehouseDetails
        if (dto.getWarehouse() != null) {
            WarehouseDetails warehouse = mapToWarehouseEntity(dto.getWarehouse());
            entity.setWarehouse(warehouse);
        }

        return entity;
    }

    public OrderDetails mapToOrderEntity(OrderDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("OrderDto cannot be null");
        }

        OrderDetails order = new OrderDetails();
        order.setOrderId(dto.getOrderId());
        order.setOrderDate(dto.getOrderDate());
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());

        // Ensure product and supplier IDs are not null
        if (dto.getProduct() != null && dto.getProduct().getId() != null) {
            ProductDetails product = productRepository.findById(dto.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + dto.getProduct().getId()));
            order.setProduct(product);
        } else {
            throw new IllegalArgumentException("Product ID must not be null");
        }

        if (dto.getSupplier() != null && dto.getSupplier().getId() != null) {
            SupplierDetails supplier = supplierRepository.findById(dto.getSupplier().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found with ID: " + dto.getSupplier().getId()));
            order.setSupplier(supplier);
        } else {
            throw new IllegalArgumentException("Supplier ID must not be null");
        }

        return order;
    }


    public SupplierDetails mapToSupplierEntity(SupplierDto supplierDto) {
        if (supplierDto == null) {
            return null;
        }

        SupplierDetails supplier = new SupplierDetails();
        supplier.setSupplierId(supplierDto.getSupplierId());
        supplier.setName(supplierDto.getName());
        supplier.setContactInformation(supplierDto.getContactInformation());

        // Products mapping should be handled in the service layer
        // List<ProductDetails> products = supplierDto.getProducts().stream()
        //         .map(p -> {
        //             ProductDetails product = new ProductDetails();
        //             product.setProductId(p.getProductId());
        //             return product;
        //         })
        //         .collect(Collectors.toList());
        // supplier.setProducts(products);

        return supplier;
    }


    public WarehouseDetails mapToWarehouseEntity(WarehouseDto warehouseDto) {
        if (warehouseDto == null) {
            return null;
        }

        WarehouseDetails warehouse = new WarehouseDetails();
        warehouse.setId(warehouseDto.getId());
        warehouse.setHouseId(warehouseDto.getHouseId());
        warehouse.setName(warehouseDto.getName());
        warehouse.setLocation(warehouseDto.getLocation());
        warehouse.setContact(warehouseDto.getContact());

        return warehouse;
    }

    public ProductWithDetailsDto mapToProductWithDetailsDto(ProductDetails product) {
        if (product == null) {
            return null;
        }

        ProductWithDetailsDto dto = new ProductWithDetailsDto();
        dto.setId(product.getId());
        dto.setProductId(product.getProductId()); // Add this line
        dto.setProdName(product.getProdName());
        dto.setProdCategory(product.getProdCategory());
        dto.setProdPrice(product.getProdPrice());

        // Map inventories
        List<InventoryDto> inventories = (product.getInventories() != null) 
                ? product.getInventories().stream()
                    .map(this::mapToInventoryDto)
                    .collect(Collectors.toList())
                : List.of();
        dto.setInventories(inventories);

        // Map orders
        List<OrderDto> orders = (product.getOrders() != null) 
                ? product.getOrders().stream()
                    .map(this::mapToOrderDto)
                    .collect(Collectors.toList())
                : List.of();
        dto.setOrders(orders);

        // Map suppliers
        List<SupplierDto> suppliers = (product.getSuppliers() != null) 
                ? product.getSuppliers().stream()
                    .map(this::mapToSupplierDto)
                    .collect(Collectors.toList())
                : List.of();
        dto.setSuppliers(suppliers);

        // Map warehouses
        List<WarehouseDto> warehouses = (product.getWarehouses() != null) 
                ? product.getWarehouses().stream()
                    .map(this::mapToWarehouseDto)
                    .collect(Collectors.toList())
                : List.of();
        dto.setWarehouses(warehouses);

        return dto;
    }

    // Map to basic Product DTO
    public ProductDto mapToProductBasicDto(ProductDetails product) {
        if (product == null) {
            return null;
        }

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProductId(product.getProductId()); // Add this line
        dto.setProdName(product.getProdName());
        dto.setProdCategory(product.getProdCategory());
        dto.setProdPrice(product.getProdPrice());

        return dto;
    }

    public InventoryDto mapToInventoryDto(InventoryDetails entity) {
        if (entity == null) {
            throw new IllegalArgumentException("InventoryDetails cannot be null");
        }

        return InventoryDto.builder()
                .id(entity.getId())
                .inventId(entity.getInventId())
                .quantity(entity.getQuantity())
                .product(mapToProductBasicDto(entity.getProduct()))
                .warehouse(mapToWarehouseDto(entity.getWarehouse()))
                .build();
    }

    public OrderDto mapToOrderDto(OrderDetails order) {
        if (order == null) {
            return null;
        }

        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrderId());
        dto.setQuantity(order.getQuantity());
        dto.setOrderDate(order.getOrderDate());
        dto.setProduct(mapToProductBasicDto(order.getProduct()));

        return dto;
    }

    public SupplierDto mapToSupplierDto(SupplierDetails supplier) {
        if (supplier == null) {
            return null;
        }

        SupplierDto dto = new SupplierDto();
        dto.setSupplierId(supplier.getSupplierId());
        dto.setName(supplier.getName());
        dto.setContactInformation(supplier.getContactInformation());

        return dto;
    }

    public WarehouseDto mapToWarehouseDto(WarehouseDetails warehouse) {
        if (warehouse == null) {
            return null;
        }

        WarehouseDto dto = new WarehouseDto();
        dto.setId(warehouse.getId());
        dto.setHouseId(warehouse.getHouseId());
        dto.setName(warehouse.getName());
        dto.setLocation(warehouse.getLocation());
        dto.setContact(warehouse.getContact());

        return dto;
    }
}
