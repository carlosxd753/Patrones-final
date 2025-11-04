package com.financorp.serf.repository;

import com.financorp.serf.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByTipo(String tipo);

    List<Reporte> findByFechaGeneracionBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Reporte> findByUsuarioGenerador(String usuarioGenerador);

    List<Reporte> findByTieneMarcaAgua(Boolean tieneMarcaAgua);

    List<Reporte> findByTieneFirmaDigital(Boolean tieneFirmaDigital);

    List<Reporte> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT r FROM Reporte r WHERE r.tipo = :tipo AND r.fechaGeneracion BETWEEN :inicio AND :fin")
    List<Reporte> findByTipoAndPeriodo(
            @Param("tipo") String tipo,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    List<Reporte> findAllByOrderByFechaGeneracionDesc();

    @Query("SELECT r FROM Reporte r ORDER BY r.fechaGeneracion DESC")
    List<Reporte> findReportesRecientes();

    Long countByTipo(String tipo);

    @Query("SELECT COUNT(r) FROM Reporte r WHERE r.fechaGeneracion BETWEEN :inicio AND :fin")
    Long countReportesByPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    @Query("SELECT r FROM Reporte r WHERE r.tieneMarcaAgua = true OR r.tieneFirmaDigital = true")
    List<Reporte> findReportesConSeguridad();

    @Query("SELECT r FROM Reporte r WHERE r.tieneMarcaAgua = false AND r.tieneFirmaDigital = false")
    List<Reporte> findReportesSinSeguridad();

    @Query("SELECT r FROM Reporte r WHERE r.tipo = :tipo ORDER BY r.fechaGeneracion DESC")
    Optional<Reporte> findUltimoReportePorTipo(@Param("tipo") String tipo);

    boolean existsByNombreArchivo(String nombreArchivo);

    @Query("SELECT r.tipo, COUNT(r) FROM Reporte r GROUP BY r.tipo ORDER BY COUNT(r) DESC")
    List<Object[]> obtenerEstadisticasPorTipo();

    @Query("SELECT r FROM Reporte r WHERE YEAR(r.fechaGeneracion) = :anio AND MONTH(r.fechaGeneracion) = :mes")
    List<Reporte> findByMesAnio(@Param("anio") int anio, @Param("mes") int mes);
}
