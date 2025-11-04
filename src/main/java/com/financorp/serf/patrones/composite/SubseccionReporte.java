package com.financorp.serf.patrones.composite;

public class SubseccionReporte extends SeccionReporte {

    public SubseccionReporte(String nombre, String contenido) {
        super(nombre, contenido);
    }

    @Override
    public String generarContenido() {
        return "   → " + nombre + ": " + contenido + "\n";
    }
}