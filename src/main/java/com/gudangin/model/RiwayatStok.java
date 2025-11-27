package com.gudangin.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "riwayat_stok")
public class RiwayatStok {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "produk_id")
    private Produk produk;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JenisTransaksi jenis;
    
    @Column(nullable = false)
    private Integer jumlah;
    
    @Column(nullable = false)
    private LocalDate tanggal;
    
    @Column(columnDefinition = "TEXT")
    private String keterangan;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public enum JenisTransaksi {
        masuk, keluar
    }
}