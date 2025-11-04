package com.financorp.serf.patrones.decorator;

import com.financorp.serf.patrones.composite.ComponenteReporte;

import java.time.LocalDateTime;

public class FirmaDigitalDecorator extends ReporteDecorator {

    private String firmante;

    public FirmaDigitalDecorator(ComponenteReporte componente, String firmante) {
        super(componente);
        this.firmante = firmante;
    }

    @Override
    public String generarContenido() {
        String contenidoBase = super.generarContenido();
        return contenidoBase + "\n\n[Firma Digital: " + firmante + " - Fecha: " + LocalDateTime.now() + "]";
    }
}