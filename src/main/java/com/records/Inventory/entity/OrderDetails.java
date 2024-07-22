package com.records.Inventory.entity;

import java.time.LocalDate;

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
@Table(name = "order_details") // Ensure table name matches the database schema
public class OrderDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "order_id")
    private Integer orderId;
    
    @Column(name = "order_date")
    private LocalDate orderDate;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "price")
    private Double totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierDetails supplier;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDetails product;
}
