package com.financorp.serf.repository;

import com.financorp.serf.entity.Producto;
import com.financorp.serf.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    Optional<Venta> findByNumeroFactura(String numeroFactura);

    List<Venta> findByProducto(Producto producto);

    List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Venta> findByPais(String pais);

    List<Venta> findByVendedor(String vendedor);

    List<Venta> findByMetodoPago(String metodoPago);

    List<Venta> findByCliente(String cliente);

    @Query("SELECT SUM(v.precioTotalEur) FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
    BigDecimal sumTotalIngresosByPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    @Query("SELECT SUM(v.precioTotalEur) FROM Venta v WHERE v.pais = :pais AND v.fechaVenta BETWEEN :inicio AND :fin")
    BigDecimal sumTotalIngresosByPaisPeriodo(
            @Param("pais") String pais,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("SELECT COUNT(v) FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
    Long countVentasByPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    @Query("SELECT v.producto FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin " +
            "GROUP BY v.producto ORDER BY SUM(v.cantidad) DESC")
    List<Producto> findProductosMasVendidosByPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    List<Venta> findAllByOrderByFechaVentaDesc();

    @Query("SELECT v FROM Venta v ORDER BY v.fechaVenta DESC")
    List<Venta> findVentasRecientes();

    @Query("SELECT SUM(v.cantidad) FROM Venta v WHERE v.producto = :producto")
    Long sumCantidadByProducto(@Param("producto") Producto producto);

    @Query("SELECT v FROM Venta v WHERE v.producto.categoria = :categoria AND v.fechaVenta BETWEEN :inicio AND :fin")
    List<Venta> findByCategoriaProductoAndPeriodo(
            @Param("categoria") String categoria,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("SELECT AVG(v.precioTotalEur) FROM Venta v")
    BigDecimal calcularPromedioVenta();

    boolean existsByNumeroFactura(String numeroFactura);

    @Query("SELECT v.pais, SUM(v.precioTotalEur) FROM Venta v " +
            "WHERE v.fechaVenta BETWEEN :inicio AND :fin " +
            "GROUP BY v.pais ORDER BY SUM(v.precioTotalEur) DESC")
    List<Object[]> obtenerIngresosPorPais(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}
