package com.financorp.serf.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El código del producto es obligatorio")
    @Size(max = 50, message = "El código no puede exceder 50 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 200, message = "El nombre no puede exceder 200 caracteres")
    private String nombre;

    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotNull(message = "El precio de importación es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio de importación debe ser mayor a 0")
    private BigDecimal precioImportacion;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio de venta debe ser mayor a 0")
    private BigDecimal precioVenta;

    @NotBlank(message = "La moneda de importación es obligatoria")
    @Pattern(regexp = "CNY|USD|EUR", message = "Moneda debe ser CNY, USD o EUR")
    private String monedaImportacion;

    @Size(max = 200, message = "El nombre del proveedor no puede exceder 200 caracteres")
    private String proveedor;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    private LocalDateTime fechaImportacion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    private BigDecimal precioImportacionEur;
    private BigDecimal precioVentaEur;
}
