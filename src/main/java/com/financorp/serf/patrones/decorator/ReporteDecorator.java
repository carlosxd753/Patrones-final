package com.financorp.serf.patrones.decorator;

import com.financorp.serf.patrones.composite.ComponenteReporte;

public abstract class ReporteDecorator implements ComponenteReporte {

    protected ComponenteReporte componente;

    public ReporteDecorator(ComponenteReporte componente) {
        this.componente = componente;
    }

    @Override
    public String obtenerNombre() {
        return componente.obtenerNombre();
    }

    @Override
    public String generarContenido() {
        return componente.generarContenido();
    }
}