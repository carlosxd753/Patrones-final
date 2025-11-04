package com.financorp.serf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes", indexes = {
        @Index(name = "idx_reporte_fecha", columnList = "fecha_generacion"),
        @Index(name = "idx_reporte_tipo", columnList = "tipo")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "ruta_archivo", nullable = false, length = 500)
    private String rutaArchivo;

    @Column(name = "nombre_archivo", nullable = false, length = 200)
    private String nombreArchivo;

    @Column(name = "tiene_marca_agua")
    private Boolean tieneMarcaAgua;

    @Column(name = "tiene_firma_digital")
    private Boolean tieneFirmaDigital;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fechaGeneracion;

    @Column(name = "usuario_generador", length = 100)
    private String usuarioGenerador;

    @PrePersist
    protected void onCreate() {
        if (fechaGeneracion == null) {
            fechaGeneracion = LocalDateTime.now();
        }

        if (tieneMarcaAgua == null) {
            tieneMarcaAgua = false;
        }

        if (tieneFirmaDigital == null) {
            tieneFirmaDigital = false;
        }

        if (usuarioGenerador == null) {
            usuarioGenerador = "Sistema";
        }
    }

}
