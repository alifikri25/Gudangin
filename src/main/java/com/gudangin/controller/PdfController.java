package com.gudangin.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @GetMapping("/generate")
    public void generatePdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=laporan_produk.pdf");

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL, BaseColor.GRAY);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font cellFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

        Paragraph title = new Paragraph("Laporan Data Produk", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        Paragraph subtitle = new Paragraph("Data Lengkap Produk Gudang", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        subtitle.setSpacingAfter(20);
        document.add(subtitle);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1, 3, 2, 2, 1});

        String[] headers = {"ID", "Nama Produk", "Kategori ID", "Harga", "Stok"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);
        }

        String[][] data = {
            {"1", "poco F7", "1", "Rp 5.000.000", "99"},
            {"2", "bakpia kukus", "2", "Rp 40.000", "5"},
            {"3", "teh pucuk", "3", "Rp 2.500", "200"},
            {"4", "biled", "4", "Rp 2.000.000", "1232"}
        };

        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(row[i], cellFont));
                cell.setPadding(5);
                if (i == 0 || i == 2 || i == 4) {
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                } else if (i == 3) {
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }
                table.addCell(cell);
            }
        }

        document.add(table);
        document.close();
    }
}