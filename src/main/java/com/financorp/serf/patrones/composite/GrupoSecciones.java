package com.financorp.serf.patrones.composite;

import java.util.ArrayList;
import java.util.List;

public class GrupoSecciones implements ComponenteReporte {

    private String nombre;
    private List<ComponenteReporte> componentes = new ArrayList<>();

    public GrupoSecciones(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(ComponenteReporte componente) {
        componentes.add(componente);
    }

    @Override
    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public String generarContenido() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ").append(nombre).append(" ===\n");
        for (ComponenteReporte componente : componentes) {
            sb.append(componente.generarContenido());
        }
        return sb.toString();
    }
}