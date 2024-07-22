package com.records.Inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Inventory_details")
public class InventoryDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
	
	@Column(name = "invent_id")
	private Integer inventId;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDetails product;
    
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseDetails warehouse;
    
    @Column(name = "quantity")
    private Integer quantity;

}
