package com.financorp.serf.patrones.prototype;

public interface PlantillaReporte extends Cloneable{

    PlantillaReporte clonar();

    String obtenerTitulo();

    void establecerTitulo(String titulo);

    String obtenerTipo();

    String obtenerFormato();
}