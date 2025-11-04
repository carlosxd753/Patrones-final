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
public class VentaDTO {

    private Long id;

    @NotBlank(message = "El número de factura es obligatorio")
    @Size(max = 50, message = "El número de factura no puede exceder 50 caracteres")
    private String numeroFactura;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    private String productoNombre;
    private String productoCategoria;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a 0")
    private BigDecimal precioUnitario;

    private BigDecimal precioTotal;

    @NotBlank(message = "La moneda local es obligatoria")
    @Pattern(regexp = "PEN|USD|EUR|CNY", message = "Moneda debe ser PEN, USD, EUR o CNY")
    private String monedaLocal;

    private BigDecimal precioTotalEur;

    @Size(max = 200, message = "El nombre del cliente no puede exceder 200 caracteres")
    private String cliente;

    @Size(max = 200, message = "El nombre del vendedor no puede exceder 200 caracteres")
    private String vendedor;

    @Pattern(regexp = "EFECTIVO|TARJETA|TRANSFERENCIA",
            message = "Método de pago debe ser EFECTIVO, TARJETA o TRANSFERENCIA")
    private String metodoPago;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    private String pais;

    private LocalDateTime fechaVenta;

    private Integer stockDisponible;
}
