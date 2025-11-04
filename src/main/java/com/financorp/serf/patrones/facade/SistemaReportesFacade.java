package com.financorp.serf.patrones.facade;

import com.financorp.serf.entity.Producto;
import com.financorp.serf.entity.Venta;
import com.financorp.serf.patrones.builder.ReporteFinancieroBuilder;
import com.financorp.serf.patrones.composite.GrupoSecciones;
import com.financorp.serf.patrones.composite.SubseccionReporte;
import com.financorp.serf.patrones.prototype.PlantillaReporte;
import com.financorp.serf.util.PdfGenerator;

import java.io.File;
import java.util.List;

public class SistemaReportesFacade {

    private final PdfGenerator pdfGenerator = new PdfGenerator();

    public String generarReporteFinanciero(
            String tipoReporte,
            PlantillaReporte plantillaBase,
            List<Venta> ventas,
            List<Producto> productos
    ) {
        try {
            PlantillaReporte plantillaClonada = plantillaBase.clonar();

            GrupoSecciones seccionPrincipal = construirSecciones(ventas, productos);

            ReporteFinancieroBuilder builder = new ReporteFinancieroBuilder();
            builder.construirEncabezado();
            builder.construirCuerpo();
            GrupoSecciones reporteBuilder = builder.obtenerResultado();

            reporteBuilder.agregar(seccionPrincipal);

            StringBuilder sb = new StringBuilder();

            sb.append("REPORTE: ").append(plantillaClonada.obtenerTitulo()).append("\n");
            sb.append("TIPO: ").append(tipoReporte).append("\n");

            sb.append(reporteBuilder.generarContenido()).append("\n");

            String contenidoFinal = sb.toString();

            String nombreArchivo = plantillaClonada.obtenerTitulo()
                    .replaceAll("\\s+", "_")
                    + "_" + System.currentTimeMillis() + ".pdf";
            String ruta = "reportes" + File.separator + nombreArchivo;
            new File("reportes").mkdirs();

            String textoMarcaAgua = "FINANCORP - CONFIDENCIAL";
            String firmante = "Gerente Financiero";

            pdfGenerator.generarPDF(contenidoFinal, ruta, textoMarcaAgua, firmante);

            return "Reporte generado correctamente en: " + ruta;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al generar el reporte: " + e.getMessage();
        }
    }



    private GrupoSecciones construirSecciones(List<Venta> ventas, List<Producto> productos) {
        GrupoSecciones seccionPrincipal = new GrupoSecciones("Datos de ingresos e inventario actual");

        GrupoSecciones ingresos = new GrupoSecciones("Ingresos por Producto");
        for (Venta v : ventas) {
            ingresos.agregar(new SubseccionReporte(
                    v.getProducto().getNombre(),
                    "Total: " + v.getPrecioTotal() + " " + v.getMonedaLocal()
            ));
        }

        GrupoSecciones inventario = new GrupoSecciones("Inventario Actual");
        for (Producto p : productos) {
            inventario.agregar(new SubseccionReporte(
                    p.getNombre(),
                    "Stock: " + p.getStock() + " unidades"
            ));
        }

        seccionPrincipal.agregar(ingresos);
        seccionPrincipal.agregar(inventario);

        return seccionPrincipal;
    }
}