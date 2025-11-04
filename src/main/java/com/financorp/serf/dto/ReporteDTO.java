package com.financorp.serf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

    private Long id;

    @NotBlank(message = "El título del reporte es obligatorio")
    @Size(max = 200, message = "El título no puede exceder 200 caracteres")
    private String titulo;

    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Pattern(regexp = "MENSUAL|TRIMESTRAL|ANUAL",
            message = "Tipo debe ser MENSUAL, TRIMESTRAL o ANUAL")
    private String tipo;

    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String descripcion;

    private String rutaArchivo;

    private String nombreArchivo;

    private Boolean tieneMarcaAgua;

    private Boolean tieneFirmaDigital;

    private LocalDateTime fechaGeneracion;

    @Size(max = 100, message = "El nombre de usuario no puede exceder 100 caracteres")
    private String usuarioGenerador;

    private Integer mes;  // Para reportes mensuales (1-12)
    private Integer trimestre;  // Para reportes trimestrales (1-4)
    private Integer anio;

    private Integer totalProductos;
    private Integer totalVentas;
    private String totalIngresosEur;
    private String productoMasVendido;
    private String categoriaLider;

    private String urlDescarga;
}
