package com.gudangin.controller;

import com.gudangin.model.RiwayatStok;
import com.gudangin.service.RiwayatStokService;
import com.gudangin.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/riwayat")
public class RiwayatStokController {
    
    @Autowired
    private RiwayatStokService riwayatStokService;
    
    @Autowired
    private ProdukService produkService;
    
    @GetMapping
    public String listRiwayat(Model model) {
        model.addAttribute("riwayatList", riwayatStokService.getAllRiwayat());
        return "riwayat/list";
    }
    
    @GetMapping("/tambah")
    public String formTambah(Model model) {
        model.addAttribute("riwayat", new RiwayatStok());
        model.addAttribute("produkList", produkService.getAllProduk());
        return "riwayat/form";
    }
    
    @PostMapping("/simpan")
    public String simpanRiwayat(@ModelAttribute RiwayatStok riwayat) {
        if (riwayat.getTanggal() == null) {
            riwayat.setTanggal(LocalDate.now());
        }
        riwayatStokService.saveRiwayat(riwayat);
        return "redirect:/riwayat";
    }
    
    @GetMapping("/hapus/{id}")
    public String hapusRiwayat(@PathVariable Integer id) {
        riwayatStokService.deleteRiwayat(id);
        return "redirect:/riwayat";
    }
}