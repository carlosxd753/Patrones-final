package com.financorp.serf.patrones.decorator;

import com.financorp.serf.patrones.composite.ComponenteReporte;

public class MarcaAguaDecorator extends ReporteDecorator {

    private String textoMarcaAgua;

    public MarcaAguaDecorator(ComponenteReporte componente, String textoMarcaAgua) {
        super(componente);
        this.textoMarcaAgua = textoMarcaAgua;
    }

    @Override
    public String generarContenido() {
        String contenidoBase = super.generarContenido();
        return contenidoBase + "\n\n--- [Marca de Agua: " + textoMarcaAgua + "] ---";
    }
}