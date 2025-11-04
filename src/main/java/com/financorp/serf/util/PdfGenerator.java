package com.financorp.serf.util;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class PdfGenerator {

    public String generarPDF(String contenido, String rutaDestino, String textoMarcaAgua, String firmante) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
        String fechaFormateada = ahora.format(formato);

        try (PdfWriter writer = new PdfWriter(new FileOutputStream(rutaDestino));


             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf, PageSize.A4)) {

            PdfFont font = PdfFontFactory.createFont();
            document.setFont(font);

            Paragraph titulo = new Paragraph("REPORTE FINANCIERO CORPORATIVO")
                    .setFontSize(16)
                    .setFontColor(ColorConstants.BLACK)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);

            document.add(new Paragraph(fechaFormateada)
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n"));

            // Contenido principal
            document.add(new Paragraph(contenido).setFontSize(11));

            // Marca de agua
            if (textoMarcaAgua != null && !textoMarcaAgua.isBlank()) {
                agregarMarcaAgua(pdf, textoMarcaAgua);
            }

            // Firma digital simulada
            if (firmante != null && !firmante.isBlank()) {
                agregarFirmaDigital(document, firmante);
            }

            return rutaDestino;

        } catch (IOException e) {
            throw new RuntimeException("Error al generar PDF: " + e.getMessage(), e);
        }
    }

    private void agregarMarcaAgua(PdfDocument pdf, String texto) throws IOException {
        PdfFont font = PdfFontFactory.createFont();

        for (int i = 1; i <= pdf.getNumberOfPages(); i++) {
            PdfPage page = pdf.getPage(i);
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdf);

            canvas.saveState();
            canvas.beginText();
            canvas.setFontAndSize(font, 50);
            canvas.setFillColor(ColorConstants.LIGHT_GRAY);

            com.itextpdf.layout.Canvas layoutCanvas =
                    new com.itextpdf.layout.Canvas(canvas, page.getPageSize(), false);

            layoutCanvas.showTextAligned(
                    texto,
                    page.getPageSize().getWidth() / 2,
                    page.getPageSize().getHeight() / 2,
                    TextAlignment.CENTER,
                    VerticalAlignment.MIDDLE,
                    (float) Math.toRadians(45)
            );

            canvas.endText();
            canvas.restoreState();
        }
    }

    private void agregarFirmaDigital(Document document, String firmante) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
        String fechaFormateada = ahora.format(formato);

        document.add(new Paragraph("\n\n_________________________________________")
                .setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph("Firmado digitalmente por: " + firmante)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph(fechaFormateada)
                .setFontSize(9)
                .setTextAlignment(TextAlignment.RIGHT));
    }

    public Table crearTabla(List<String[]> datos) {
        if (datos == null || datos.isEmpty()) {
            Table t = new Table(1);
            t.addCell(new Cell().add(new Paragraph("Sin datos disponibles")));
            return t;
        }

        int columnas = datos.get(0).length;
        Table table = new Table(columnas);

        // ✅ setWidthPercent() fue reemplazado por setWidth(UnitValue.createPercentValue(x))
        table.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

        for (String[] fila : datos) {
            for (String celda : fila) {
                table.addCell(new Cell().add(new Paragraph(celda)));
            }
        }
        return table;
    }
}