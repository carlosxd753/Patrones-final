package com.financorp.serf.service;

import com.financorp.serf.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listarTodos();

    public Producto guardar(Producto producto);

    public Producto buscarPorId(Long id);

    public void eliminar(Long id);
}
