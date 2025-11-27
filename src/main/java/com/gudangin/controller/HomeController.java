package com.gudangin.controller;

import com.gudangin.service.ProdukService;
import com.gudangin.service.KategoriService;
import com.gudangin.service.RiwayatStokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private ProdukService produkService;
    
    @Autowired
    private KategoriService kategoriService;
    
    @Autowired
    private RiwayatStokService riwayatStokService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalProduk", produkService.getAllProduk().size());
        model.addAttribute("totalKategori", kategoriService.getAllKategori().size());
        model.addAttribute("totalRiwayat", riwayatStokService.getAllRiwayat().size());
        model.addAttribute("stokRendah", produkService.getProdukStokRendah(10).size());
        return "home";
    }
}