package com.records.Inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="Warehouse_details")
public class WarehouseDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "house_id")
	private Integer houseId;
    
    @Column(name = "warehouse_name")
    private String name;
    
    @Column(name = "warehouse_city")
    private String location;
    
    @Column(name = "warehouse_contact")
    private String contact;
    

}
