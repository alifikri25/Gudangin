package com.gudangin.controller;

import com.gudangin.model.Kategori;
import com.gudangin.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kategori")
public class KategoriController {
    
    @Autowired
    private KategoriService kategoriService;
    
    @GetMapping
    public String listKategori(Model model) {
        model.addAttribute("kategoriList", kategoriService.getAllKategori());
        return "kategori/list";
    }
    
    @GetMapping("/tambah")
    public String formTambah(Model model) {
        model.addAttribute("kategori", new Kategori());
        return "kategori/form";
    }
    
    @GetMapping("/edit/{id}")
    public String formEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("kategori", kategoriService.getKategoriById(id).orElseThrow());
        return "kategori/form";
    }
    
    @PostMapping("/simpan")
    public String simpanKategori(@ModelAttribute Kategori kategori) {
        kategoriService.saveKategori(kategori);
        return "redirect:/kategori";
    }
    
    @GetMapping("/hapus/{id}")
    public String hapusKategori(@PathVariable Integer id) {
        kategoriService.deleteKategori(id);
        return "redirect:/kategori";
    }
}