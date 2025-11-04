package com.financorp.serf.service;

import com.financorp.serf.entity.Producto;
import com.financorp.serf.entity.Venta;
import com.financorp.serf.patrones.facade.SistemaReportesFacade;
import com.financorp.serf.patrones.prototype.PlantillaAnual;
import com.financorp.serf.patrones.prototype.PlantillaMensual;
import com.financorp.serf.patrones.prototype.PlantillaReporte;
import com.financorp.serf.patrones.prototype.PlantillaTrimestral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    private final ProductoService productoService;
    private final VentaService ventaService;
    private final SistemaReportesFacade facade;

    public ReporteService(ProductoService productoService, VentaService ventaService) {
        this.productoService = productoService;
        this.ventaService = ventaService;
        this.facade = new SistemaReportesFacade();
    }

    private String generarReporte(String tipo, PlantillaReporte plantilla) {
        List<Producto> productos = productoService.listarTodos();
        List<Venta> ventas = ventaService.listarTodas();

        return facade.generarReporteFinanciero(tipo, plantilla, ventas, productos);
    }

    public String generarReporteMensual() {
        return generarReporte("Mensual", new PlantillaMensual());
    }

    public String generarReporteTrimestral() {
        return generarReporte("Trimestral", new PlantillaTrimestral());
    }

    public String generarReporteAnual() {
        return generarReporte("Anual", new PlantillaAnual());
    }
}