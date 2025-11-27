package com.gudangin.service;

import com.gudangin.model.Kategori;
import com.gudangin.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KategoriService {
    
    @Autowired
    private KategoriRepository kategoriRepository;
    
    public List<Kategori> getAllKategori() {
        return kategoriRepository.findAll();
    }
    
    public Optional<Kategori> getKategoriById(Integer id) {
        return kategoriRepository.findById(id);
    }
    
    public Kategori saveKategori(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }
    
    public void deleteKategori(Integer id) {
        kategoriRepository.deleteById(id);
    }
}