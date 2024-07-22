package com.records.Inventory.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name="product_details")
public class ProductDetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id; // Changed from String to Long
    
    @Column(name = "product_id", unique = true)
    private Integer productId;
    
    @Column(name = "prod_name")
    private String prodName; // Changed to match Java naming conventions
    
    @Column(name = "prod_cat")
    private String prodCategory; // Changed to match Java naming conventions
    
    @Column(name = "prod_price")
    private Integer prodPrice; // Changed to match Java naming conventions
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<InventoryDetails> inventories;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderDetails> orders;
    
    @ManyToMany(mappedBy = "products")
    private List<SupplierDetails> suppliers;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_warehouse",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private List<WarehouseDetails> warehouses;
}
