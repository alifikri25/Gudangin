package com.gudangin.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "kategori")
public class Kategori {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nama_kategori", nullable = false, length = 100)
    private String namaKategori;
    
    @Column(columnDefinition = "TEXT")
    private String deskripsi;
}