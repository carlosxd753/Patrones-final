package com.financorp.serf.patrones.builder;

import com.financorp.serf.patrones.composite.GrupoSecciones;
import com.financorp.serf.patrones.composite.SeccionReporte;

public class ReporteFinancieroBuilder implements ReporteBuilder {

    private GrupoSecciones reporte;

    @Override
    public void construirEncabezado() {
        reporte = new GrupoSecciones("Reporte Financiero Corporativo");
        SeccionReporte encabezado = new SeccionReporte("Encabezado",
                "Corporación FinanCorp S.A.\nMoneda corporativa: EUR");
        reporte.agregar(encabezado);
    }

    @Override
    public void construirCuerpo() {
        GrupoSecciones cuerpo = new GrupoSecciones("Cuerpo del Reporte");

        GrupoSecciones ingresos = new GrupoSecciones("Ingresos por País");
        ingresos.agregar(new SeccionReporte("Perú", "Total ventas: €150,000"));
        ingresos.agregar(new SeccionReporte("México", "Total ventas: €95,000"));
        ingresos.agregar(new SeccionReporte("España", "Total ventas: €120,000"));

        GrupoSecciones gastos = new GrupoSecciones("Gastos de Importación");
        gastos.agregar(new SeccionReporte("Compras", "€25,000"));

        cuerpo.agregar(ingresos);
        cuerpo.agregar(gastos);
        reporte.agregar(cuerpo);
    }

    @Override
    public GrupoSecciones obtenerResultado() {
        return reporte;
    }
}