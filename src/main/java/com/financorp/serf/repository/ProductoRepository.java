package com.financorp.serf.repository;

import com.financorp.serf.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByCodigo(String codigo);

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByStockGreaterThan(Integer stock);

    List<Producto> findByStockLessThanEqual(Integer stock);

    List<Producto> findByProveedor(String proveedor);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria AND p.stock > 0")
    List<Producto> findByCategoriaConStock(@Param("categoria") String categoria);

    Long countByCategoria(String categoria);

    boolean existsByCodigo(String codigo);

    List<Producto> findAllByOrderByNombreAsc();

    @Query("SELECT p FROM Producto p ORDER BY p.stock ASC")
    List<Producto> findProductosMasVendidos();

    @Query("SELECT SUM(p.stock) FROM Producto p")
    Long obtenerStockTotal();

    @Query("SELECT DISTINCT p.categoria FROM Producto p ORDER BY p.categoria")
    List<String> obtenerCategorias();
}
