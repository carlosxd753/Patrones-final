package com.financorp.serf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos", indexes = {
        @Index(name = "idx_producto_codigo", columnList = "codigo"),
        @Index(name = "idx_producto_categoria", columnList = "categoria")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "categoria", length = 100)
    private String categoria;

    @Column(name = "precio_importacion", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioImportacion;

    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(name = "moneda_importacion", length = 10)
    private String monedaImportacion;

    @Column(name = "proveedor", length = 200)
    private String proveedor;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "fecha_importacion")
    private LocalDateTime fechaImportacion;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();

        if (fechaImportacion == null) {
            fechaImportacion = LocalDateTime.now();
        }

        if (stock == null) {
            stock = 0;
        }

        if (monedaImportacion == null) {
            monedaImportacion = "CNY";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    public void actualizarStock(Integer cantidad) {
        if (this.stock < cantidad) {
            throw new IllegalArgumentException(
                    "Stock insuficiente. Disponible: " + this.stock + ", Solicitado: " + cantidad
            );
        }
        this.stock -= cantidad;
    }

    public BigDecimal convertirPrecioAEur(BigDecimal tasaCambio) {
        if (tasaCambio == null || tasaCambio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Tasa de cambio inválida");
        }
        return precioImportacion.multiply(tasaCambio);
    }

    public boolean tieneStock() {
        return this.stock != null && this.stock > 0;
    }

    public boolean tieneSuficienteStock(Integer cantidad) {
        return this.stock != null && this.stock >= cantidad;
    }
}
