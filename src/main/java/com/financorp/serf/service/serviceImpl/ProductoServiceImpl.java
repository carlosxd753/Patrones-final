package com.financorp.serf.service.serviceImpl;

import com.financorp.serf.entity.Producto;
import com.financorp.serf.repository.ProductoRepository;
import com.financorp.serf.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID " + id));
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
