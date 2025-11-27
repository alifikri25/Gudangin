package com.gudangin.controller;

import com.gudangin.model.Produk;
import com.gudangin.service.ProdukService;
import com.gudangin.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produk")
public class ProdukController {
    
    @Autowired
    private ProdukService produkService;
    
    @Autowired
    private KategoriService kategoriService;
    
    @GetMapping
    public String listProduk(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("produkList", produkService.searchProdukByNama(search));
        } else {
            model.addAttribute("produkList", produkService.getAllProduk());
        }
        return "produk/list";
    }
    
    @GetMapping("/tambah")
    public String formTambah(Model model) {
        model.addAttribute("produk", new Produk());
        model.addAttribute("kategoriList", kategoriService.getAllKategori());
        return "produk/form";
    }
    
    @GetMapping("/edit/{id}")
    public String formEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("produk", produkService.getProdukById(id).orElseThrow());
        model.addAttribute("kategoriList", kategoriService.getAllKategori());
        return "produk/form";
    }
    
    @PostMapping("/simpan")
    public String simpanProduk(@ModelAttribute Produk produk) {
        produkService.saveProduk(produk);
        return "redirect:/produk";
    }
    
    @GetMapping("/hapus/{id}")
    public String hapusProduk(@PathVariable Integer id) {
        produkService.deleteProduk(id);
        return "redirect:/produk";
    }
}