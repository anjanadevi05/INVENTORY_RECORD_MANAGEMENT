package com.records.Inventory.service;

import com.records.Inventory.dto.InventoryDto;
import com.records.Inventory.dto.InventoryListDto;
import com.records.Inventory.dto.OrderDto;
import com.records.Inventory.dto.OrderListDto;
import com.records.Inventory.dto.ProductDto;
import com.records.Inventory.dto.ProductListDto;
import com.records.Inventory.dto.ProductWithDetailsDto;
import com.records.Inventory.dto.SupplierDto;
import com.records.Inventory.dto.SupplierListDto;
import com.records.Inventory.dto.WarehouseDto;
import com.records.Inventory.dto.WarehouseListDto;

public interface InventoryService {

    Integer saveProductInfo(ProductDto dto);
    
    Integer saveInventoryInfo(InventoryDto dto);

    Integer saveOrderInfo(OrderDto dto);

    Integer saveSupplierInfo(SupplierDto dto);

    Integer saveWarehouseInfo(WarehouseDto dto);

	InventoryDto getInventoryInfo(Integer id);

	WarehouseDto getWarehouseInfo(Integer id);

	SupplierDto getSupplierInfo(Integer id);

	OrderDto getOrderInfo(Integer id);

	/*InventoryListDto getAllInventoryInfo();

	ProductListDto getAllProductInfo();

	WarehouseListDto getAllWarehouseInfo();

	SupplierListDto getAllSupplierInfo();

	OrderListDto getAllOrderInfo();*/

	Integer updateInventoryInfo(InventoryDto dto);

	Integer updateProductInfo(ProductDto dto);

	Integer updateWarehouseInfo(WarehouseDto dto);

	Integer updateSupplierInfo(SupplierDto dto);

	Integer updateOrderInfo(OrderDto dto);

	ProductWithDetailsDto getProductDetailInfo(Integer productId);

	ProductDto getProductBasicInfo(Integer productId);
}
