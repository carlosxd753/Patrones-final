package com.financorp.serf.service;

import com.financorp.serf.dto.VentaDTO;
import com.financorp.serf.entity.Venta;
import com.financorp.serf.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    public List<VentaDTO> listarTodasDTO() {
        List<Venta> ventas = ventaRepository.findAll();

        return ventas.stream().map(v -> VentaDTO.builder()
                        .id(v.getId())
                        .numeroFactura(v.getNumeroFactura())
                        .productoId(v.getProducto().getId())
                        .productoNombre(v.getProducto().getNombre())
                        .productoCategoria(v.getProducto().getCategoria())
                        .cantidad(v.getCantidad())
                        .precioUnitario(v.getPrecioUnitario())
                        .precioTotal(v.getPrecioTotal())
                        .monedaLocal(v.getMonedaLocal())
                        .precioTotalEur(v.getPrecioTotalEur())
                        .cliente(v.getCliente())
                        .vendedor(v.getVendedor())
                        .metodoPago(v.getMetodoPago())
                        .pais(v.getPais())
                        .fechaVenta(v.getFechaVenta())
                        .stockDisponible(v.getProducto().getStock())
                        .build())
                .toList();
    }

    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID " + id));
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }
}
