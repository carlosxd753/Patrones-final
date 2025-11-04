package com.financorp.serf.controller;

import com.financorp.serf.dto.VentaDTO;
import com.financorp.serf.entity.Venta;
import com.financorp.serf.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarTodas() {
        return ResponseEntity.ok(ventaService.listarTodasDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Venta> registrar(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaService.guardar(venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
