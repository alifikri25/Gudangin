package com.gudangin.repository;

import com.gudangin.model.RiwayatStok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RiwayatStokRepository extends JpaRepository<RiwayatStok, Integer> {
    List<RiwayatStok> findByProdukId(Integer produkId);
    List<RiwayatStok> findByTanggalBetween(LocalDate start, LocalDate end);
    List<RiwayatStok> findByJenis(RiwayatStok.JenisTransaksi jenis);
}