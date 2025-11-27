package com.gudangin.service;

import com.gudangin.model.Produk;
import com.gudangin.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdukService {
    
    @Autowired
    private ProdukRepository produkRepository;
    
    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }
    
    public Optional<Produk> getProdukById(Integer id) {
        return produkRepository.findById(id);
    }
    
    public List<Produk> searchProdukByNama(String nama) {
        return produkRepository.findByNamaProdukContainingIgnoreCase(nama);
    }
    
    public List<Produk> getProdukByKategori(Integer kategoriId) {
        return produkRepository.findByKategoriId(kategoriId);
    }
    
    public List<Produk> getProdukStokRendah(Integer minStok) {
        return produkRepository.findByStokLessThan(minStok);
    }
    
    public Produk saveProduk(Produk produk) {
        return produkRepository.save(produk);
    }
    
    public void deleteProduk(Integer id) {
        produkRepository.deleteById(id);
    }
    
    public void updateStok(Integer produkId, Integer jumlah) {
        Optional<Produk> produkOpt = produkRepository.findById(produkId);
        if (produkOpt.isPresent()) {
            Produk produk = produkOpt.get();
            produk.setStok(produk.getStok() + jumlah);
            produkRepository.save(produk);
        }
    }
}