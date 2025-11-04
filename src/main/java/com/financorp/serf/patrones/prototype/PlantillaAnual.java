package com.financorp.serf.patrones.prototype;

public class PlantillaAnual implements PlantillaReporte {

    private String titulo;
    private String tipo;
    private String formato;

    public PlantillaAnual() {
        this.titulo = "Reporte Anual Corporativo";
        this.tipo = "ANUAL";
        this.formato = "PDF";
    }

    @Override
    public PlantillaReporte clonar() {
        try {
            PlantillaAnual clon = (PlantillaAnual) super.clone();
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar PlantillaAnual", e);
        }
    }

    @Override
    public String obtenerTitulo() {
        return titulo;
    }

    @Override
    public void establecerTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String obtenerTipo() {
        return tipo;
    }

    @Override
    public String obtenerFormato() {
        return formato;
    }

    @Override
    public String toString() {
        return "PlantillaAnual{" +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", formato='" + formato + '\'' +
                '}';
    }
}
