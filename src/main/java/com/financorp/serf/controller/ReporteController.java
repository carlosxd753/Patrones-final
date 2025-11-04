package com.financorp.serf.controller;

import com.financorp.serf.service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/mensual")
    public ResponseEntity<String> generarReporteMensual() {
        String resultado = reporteService.generarReporteMensual();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/trimestral")
    public ResponseEntity<String> generarReporteTrimestral() {
        String resultado = reporteService.generarReporteTrimestral();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/anual")
    public ResponseEntity<String> generarReporteAnual() {
        String resultado = reporteService.generarReporteAnual();
        return ResponseEntity.ok(resultado);
    }
}