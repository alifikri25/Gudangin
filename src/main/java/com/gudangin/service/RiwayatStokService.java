package com.gudangin.service;

import com.gudangin.model.RiwayatStok;
import com.gudangin.repository.RiwayatStokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RiwayatStokService {
    
    @Autowired
    private RiwayatStokRepository riwayatStokRepository;
    
    @Autowired
    private ProdukService produkService;
    
    public List<RiwayatStok> getAllRiwayat() {
        return riwayatStokRepository.findAll();
    }
    
    public Optional<RiwayatStok> getRiwayatById(Integer id) {
        return riwayatStokRepository.findById(id);
    }
    
    public List<RiwayatStok> getRiwayatByProduk(Integer produkId) {
        return riwayatStokRepository.findByProdukId(produkId);
    }
    
    public List<RiwayatStok> getRiwayatByTanggal(LocalDate start, LocalDate end) {
        return riwayatStokRepository.findByTanggalBetween(start, end);
    }
    
    public List<RiwayatStok> getRiwayatByJenis(RiwayatStok.JenisTransaksi jenis) {
        return riwayatStokRepository.findByJenis(jenis);
    }
    
    public RiwayatStok saveRiwayat(RiwayatStok riwayat) {
        RiwayatStok saved = riwayatStokRepository.save(riwayat);
        
        int jumlahPerubahan = riwayat.getJumlah();
        if (riwayat.getJenis() == RiwayatStok.JenisTransaksi.keluar) {
            jumlahPerubahan = -jumlahPerubahan;
        }
        
        produkService.updateStok(riwayat.getProduk().getId(), jumlahPerubahan);
        
        return saved;
    }
    
    public void deleteRiwayat(Integer id) {
        riwayatStokRepository.deleteById(id);
    }
}