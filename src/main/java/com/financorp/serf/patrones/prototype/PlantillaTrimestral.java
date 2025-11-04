package com.financorp.serf.patrones.prototype;

public class PlantillaTrimestral implements PlantillaReporte {

    private String titulo;
    private String tipo;
    private String formato;
    private String estructura;

    public PlantillaTrimestral() {
        this.titulo = "Reporte Trimestral Consolidado";
        this.tipo = "TRIMESTRAL";
        this.formato = "PDF";
    }


    @Override
    public PlantillaReporte clonar() {
        try {
            PlantillaTrimestral clon = (PlantillaTrimestral) super.clone();
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar PlantillaTrimestral", e);
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
        return "PlantillaTrimestral{" +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", formato='" + formato + '\'' +
                '}';
    }
}
