package com.financorp.serf.patrones.builder;

import com.financorp.serf.patrones.composite.GrupoSecciones;

public interface ReporteBuilder {
    void construirEncabezado();
    void construirCuerpo();
    GrupoSecciones obtenerResultado();
}