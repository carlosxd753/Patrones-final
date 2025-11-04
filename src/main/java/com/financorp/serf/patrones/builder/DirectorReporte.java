package com.financorp.serf.patrones.builder;

import com.financorp.serf.patrones.composite.GrupoSecciones;

public class DirectorReporte {

    private ReporteBuilder builder;

    public DirectorReporte(ReporteBuilder builder) {
        this.builder = builder;
    }

    public GrupoSecciones construirReporteCompleto() {
        builder.construirEncabezado();
        builder.construirCuerpo();
        return builder.obtenerResultado();
    }
}