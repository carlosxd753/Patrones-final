package com.financorp.serf.patrones.prototype;

public class PlantillaMensual implements PlantillaReporte {

    private String titulo;
    private String tipo;
    private String formato;
    private String estructura;

    public PlantillaMensual() {
        this.titulo = "Reporte Mensual de Ventas e Ingresos";
        this.tipo = "MENSUAL";
        this.formato = "PDF";
    }

    @Override
    public PlantillaReporte clonar() {
        try {
            PlantillaMensual clon = (PlantillaMensual) super.clone();

            return clon;

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar PlantillaMensual", e);
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
        return "PlantillaMensual{" +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", formato='" + formato + '\'' +
                '}';
    }
}
