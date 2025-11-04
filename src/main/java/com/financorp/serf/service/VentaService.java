package com.financorp.serf.service;

import com.financorp.serf.dto.VentaDTO;
import com.financorp.serf.entity.Venta;

import java.util.List;

public interface VentaService {
    public List<Venta> listarTodas();

    public List<VentaDTO> listarTodasDTO();

    public Venta guardar(Venta venta);

    public Venta buscarPorId(Long id);

    public void eliminar(Long id);
}
