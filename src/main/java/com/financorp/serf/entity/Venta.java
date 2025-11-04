package com.financorp.serf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas", indexes = {
        @Index(name = "idx_venta_producto", columnList = "producto_id"),
        @Index(name = "idx_venta_fecha", columnList = "fecha_venta"),
        @Index(name = "idx_venta_pais", columnList = "pais")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_factura", nullable = false, unique = true, length = 50)
    private String numeroFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonIgnore
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "precio_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioTotal;

    @Column(name = "moneda_local", length = 10)
    private String monedaLocal;

    @Column(name = "precio_total_eur", precision = 10, scale = 2)
    private BigDecimal precioTotalEur;

    @Column(name = "cliente", length = 200)
    private String cliente;

    @Column(name = "vendedor", length = 200)
    private String vendedor;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @Column(name = "pais", length = 50)
    private String pais;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @PrePersist
    protected void onCreate() {
        if (fechaVenta == null) {
            fechaVenta = LocalDateTime.now();
        }

        if (monedaLocal == null) {
            monedaLocal = "PEN";
        }

        if (pais == null) {
            pais = "Peru";
        }

        // Calcular el precio total si no está definido
        if (precioTotal == null && precioUnitario != null && cantidad != null) {
            calcularTotal();
        }
    }

    public void calcularTotal() {
        if (cantidad != null && precioUnitario != null) {
            this.precioTotal = precioUnitario.multiply(new BigDecimal(cantidad));
        }
    }
}
