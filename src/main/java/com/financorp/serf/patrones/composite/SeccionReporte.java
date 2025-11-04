package com.financorp.serf.patrones.composite;

public class SeccionReporte implements ComponenteReporte {

    protected String nombre;
    protected String contenido;

    public SeccionReporte(String nombre, String contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
    }

    @Override
    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public String generarContenido() {
        return "== " + nombre + " ==\n" + contenido + "\n";
    }
}