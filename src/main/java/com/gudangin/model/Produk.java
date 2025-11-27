package com.gudangin.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "produk")
public class Produk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nama_produk", nullable = false)
    private String namaProduk;
    
    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal harga;
    
    @Column(nullable = false)
    private Integer stok = 0;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}