package com.gudangin.repository;

import com.gudangin.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Integer> {
    List<Produk> findByNamaProdukContainingIgnoreCase(String nama);
    List<Produk> findByKategoriId(Integer kategoriId);
    List<Produk> findByStokLessThan(Integer stok);
}