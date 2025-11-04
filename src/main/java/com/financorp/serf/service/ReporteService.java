package com.financorp.serf.service;

import com.financorp.serf.patrones.prototype.PlantillaReporte;

public interface ReporteService {
    public String generarReporte(String tipo, PlantillaReporte plantilla);

    public String generarReporteMensual();

    public String generarReporteTrimestral();

    public String generarReporteAnual();
}