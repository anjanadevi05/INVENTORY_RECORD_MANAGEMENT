package com.records.Inventory.controller;

import static com.records.Inventory.constant.InventoryConstant.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.records.Inventory.dto.*;
import com.records.Inventory.entity.ProductDetails;
import com.records.Inventory.map.EntityMapper;
import com.records.Inventory.repository.ProductRepository;
import com.records.Inventory.response.CommanResponse;
import com.records.Inventory.service.InventoryService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityMapper entityMapper;

    @GetMapping("/product")
    public ProductDto getProductInfo() {
        return ProductDto.builder().build();
    }

    @GetMapping("/order")
    public OrderDto getOrderInfo() {
        return OrderDto.builder().build();
    }

    @GetMapping("/inventory")
    public InventoryDto getInventoryInfo() {
        return InventoryDto.builder().build();
    }

    @GetMapping("/warehouse")
    public WarehouseDto getWarehouseInfo() {
        return WarehouseDto.builder().build();
    }

    @GetMapping("/supplier")
    public SupplierDto getSupplierInfo() {
        return SupplierDto.builder().build();
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<CommanResponse<InventoryDto>> getInventoryInfo(@PathVariable Integer id) {
        log.info("InventoryServiceImpl :: getInventoryInfo");
        InventoryDto inventoryInfo = inventoryService.getInventoryInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommanResponse.<InventoryDto>builder()
                .isError(false)
                .data(inventoryInfo)
                .message(INVENTORY_INFO_FETCH_MESSAGE)
                .build());
    }

    
    @GetMapping("/product/{id}")
    public ResponseEntity<CommanResponse<ProductDto>> getProductInfo(@PathVariable Integer id) {
        ProductDto productDto = inventoryService.getProductBasicInfo(id);
        return ResponseEntity.ok(CommanResponse.<ProductDto>builder()
                .isError(false)
                .data(productDto)
                .message("Product information fetched successfully.")
                .build());
    }

    // Get detailed product information
    @GetMapping("/detail/{id}")
    public ResponseEntity<ProductWithDetailsDto> getProductDetail(@PathVariable("id") Integer productId) {
        ProductWithDetailsDto productDetailDto = inventoryService.getProductDetailInfo(productId);
        return ResponseEntity.ok(productDetailDto);
    }

    


    @GetMapping("/warehouse/{id}")
    public ResponseEntity<CommanResponse<WarehouseDto>> getWarehouseInfo(@PathVariable Integer id) {
        log.info("InventoryServiceImpl :: getWarehouseInfo");
        WarehouseDto warehouseInfo = inventoryService.getWarehouseInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommanResponse.<WarehouseDto>builder()
                .isError(false)
                .data(warehouseInfo)
                .message(WAREHOUSE_INFO_FETCH_MESSAGE)
                .build());
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<CommanResponse<SupplierDto>> getSupplierInfo(@PathVariable Integer id) {
        log.info("InventoryServiceImpl :: getSupplierInfo");
        SupplierDto supplierInfo = inventoryService.getSupplierInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommanResponse.<SupplierDto>builder()
                .isError(false)
                .data(supplierInfo)
                .message(SUPPLIER_INFO_FETCH_MESSAGE)
                .build());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<CommanResponse<OrderDto>> getOrderInfo(@PathVariable Integer id) {
        log.info("InventoryServiceImpl :: getOrderInfo");
        OrderDto orderInfo = inventoryService.getOrderInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommanResponse.<OrderDto>builder()
                .isError(false)
                .data(orderInfo)
                .message(ORDER_INFO_FETCH_MESSAGE)
                .build());
    }

    @PostMapping("/inventory")
    public ResponseEntity<CommanResponse<Integer>> saveInventoryInfo(@RequestBody InventoryDto dto) {
        log.info("InventoryServiceImpl :: saveInventoryInfo");

        Integer id = inventoryService.saveInventoryInfo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommanResponse.<Integer>builder()
                .isError(false)
                .data(id)
                .message(INVENTORY_INFO_SAVED_MESSAGE)
                .build());
    }

    @PostMapping("/product")
    public ResponseEntity<CommanResponse<Integer>> saveProductInfo(@RequestBody ProductDto dto) {
        log.info("InventoryServiceImpl :: saveProductInfo");

        ProductDto basicProductDto = ProductDto.builder()
                .productId(dto.getProductId())
                .prodName(dto.getProdName())
                .prodCategory(dto.getProdCategory())
                .prodPrice(dto.getProdPrice())
                .build();

        Integer id = inventoryService.saveProductInfo(basicProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommanResponse.<Integer>builder()
                .isError(false)
                .data(id)
                .message(PRODUCT_INFO_SAVED_MESSAGE)
                .build());
    }

    @PostMapping("/warehouse")
    public ResponseEntity<CommanResponse<Integer>> saveWarehouseInfo(@RequestBody WarehouseDto dto) {
        log.info("InventoryServiceImpl :: saveWarehouseInfo");

        WarehouseDto basicWarehouseDto = WarehouseDto.builder()
                .houseId(dto.getHouseId())
                .name(dto.getName())
                .location(dto.getLocation())
                .build();

        Integer id = inventoryService.saveWarehouseInfo(basicWarehouseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommanResponse.<Integer>builder()
                .isError(false)
                .data(id)
                .message(WAREHOUSE_INFO_SAVED_MESSAGE)
                .build());
    }
    
    @PostMapping("/supplier")
    public ResponseEntity<CommanResponse<Integer>> saveSupplierInfo(@RequestBody SupplierDto dto) {
        log.info("InventoryServiceImpl :: saveSupplierInfo");

        SupplierDto basicSupplierDto = SupplierDto.builder()
                .supplierId(dto.getSupplierId())
                .name(dto.getName())
                .contactInformation(dto.getContactInformation())
                .products(dto.getProducts())
                .build();

        Integer id = inventoryService.saveSupplierInfo(basicSupplierDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommanResponse.<Integer>builder()
                .isError(false)
                .data(id)
                .message(SUPPLIER_INFO_SAVED_MESSAGE)
                .build());
    }

    /*@PostMapping("/supplier")
    public ResponseEntity<Integer> saveSupplierInfo(@RequestBody SupplierDto supplierDto) {
        Integer supplierId = inventoryService.saveSupplierInfo(supplierDto);
        return new ResponseEntity<>(supplierId, HttpStatus.CREATED);
    }*/


    @PostMapping("/order")
    public ResponseEntity<CommanResponse<Integer>> saveOrderInfo(@RequestBody OrderDto dto) {
        log.info("InventoryController :: saveOrderInfo");

        // Log and save order info
        try
        {
        	Integer id = inventoryService.saveOrderInfo(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(CommanResponse.<Integer>builder()
                    .isError(false)
                    .data(id)
                    .message(ORDER_INFO_SAVED_MESSAGE)
                    .build());
        }
        catch(Exception e)
        {
        	log.error("Error saving order information", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommanResponse.<Integer>builder()
                    .isError(true)
                    .message(e.getMessage())
                    .build());
        }
        
    }


    @PutMapping("/inventory")
    public ResponseEntity<CommanResponse<Integer>> updateInventoryInfo(@RequestBody InventoryDto dto) {
        log.info("InventoryServiceImpl :: updateInventoryInfo");

        // Validate DTO
        if (dto == null || dto.getId() == null) {
            log.error("Invalid InventoryDto provided for update.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CommanResponse.<Integer>builder()
                    .isError(true)
                    .message("Invalid inventory data.")
                    .build()
            );
        }

        try {
            Integer id = inventoryService.updateInventoryInfo(dto);
            return ResponseEntity.status(HttpStatus.OK).body(CommanResponse.<Integer>builder()
                    .isError(false)
                    .data(id)
                    .message("Inventory information updated successfully.")
                    .build());
        } catch (Exception e) {
            log.error("Error updating inventory info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    CommanResponse.<Integer>builder()
                    .isError(true)
                    .message("Failed to update inventory information.")
                    .build()
            );
        }
    }


    @PutMapping("/product")
    public ResponseEntity<CommanResponse<Integer>> updateProductInfo(@RequestBody ProductDto dto) {
        log.info("InventoryServiceImpl :: updateProductInfo");
        Integer id = inventoryService.updateProductInfo(dto);
        return ResponseEntity.status(HttpStatus.OK).body(CommanResponse.<Integer>builder()
                .isError(false)
                .data(id)
                .message(PRODUCT_INFO_SAVED_MESSAGE)
                .build());
    }

    @PutMapping("/warehouse")
    public ResponseEntity<CommanResponse<Integer>> updateWarehouseInfo(@RequestBody WarehouseDto dto) {
        log.info("InventoryServiceImpl :: updateWarehouseInfo");
        Integer id = inventoryService.updateWarehouseInfo(dto);
        return ResponseEntity.status(HttpStatus.OK).body(CommanResponse.<Integer>builder()
                .isError(false)
                .data(id)
                .message(WAREHOUSE_INFO_SAVED_MESSAGE)
                .build());
    }

    @PutMapping("/supplier")
    public ResponseEntity<Integer> updateSupplierInfo(@RequestBody SupplierDto supplierDto) {
        Integer supplierId = inventoryService.updateSupplierInfo(supplierDto);
        return new ResponseEntity<>(supplierId, HttpStatus.OK);
    }
    
    @PutMapping("/order")
    public ResponseEntity<CommanResponse<Integer>> updateOrderInfo(@RequestBody OrderDto dto) {
        try {
            Integer id = inventoryService.updateOrderInfo(dto);
            return ResponseEntity.ok(CommanResponse.<Integer>builder()
                    .isError(false)
                    .data(id)
                    .message("Order info updated successfully")
                    .build());
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CommanResponse.<Integer>builder()
                    .isError(true)
                    .data(null)
                    .message(ex.getMessage())
                    .build());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommanResponse.<Integer>builder()
                    .isError(true)
                    .data(null)
                    .message("Failed to update order info")
                    .build());
        }
    }


    



}
