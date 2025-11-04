package com.financorp.serf.patrones.singleton;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConfiguracionGlobal {

    private static ConfiguracionGlobal instancia;

    @Value("${serf.moneda.corporativa:EUR}")
    private String monedaCorporativa;

    @Value("${serf.tasa.cny-eur:0.13}")
    private BigDecimal tasaCnyEur;

    @Value("${serf.tasa.pen-eur:0.24}")
    private BigDecimal tasaPenEur;

    @Value("${serf.tasa.usd-eur:0.95}")
    private BigDecimal tasaUsdEur;

    private Map<String, BigDecimal> tasasCambio;

    private ConfiguracionGlobal() {
        this.tasasCambio = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        instancia = this;

        tasasCambio.put("CNY", tasaCnyEur);  // Yuan chino a EUR
        tasasCambio.put("PEN", tasaPenEur);  // Sol peruano a EUR
        tasasCambio.put("USD", tasaUsdEur);  // Dólar americano a EUR
        tasasCambio.put("EUR", BigDecimal.ONE); // EUR a EUR (tasa 1:1)
    }

    public static ConfiguracionGlobal obtenerInstancia() {
        if (instancia == null) {
            throw new IllegalStateException(
                    "ConfiguracionGlobal no ha sido inicializado por Spring. " +
                            "Asegúrese de que la aplicación Spring Boot esté iniciada."
            );
        }
        return instancia;
    }

    public String obtenerMonedaCorporativa() {
        return monedaCorporativa;
    }

    public BigDecimal obtenerTasaCambio(String moneda) {
        if (moneda == null || moneda.isEmpty()) {
            throw new IllegalArgumentException("El código de moneda no puede ser nulo o vacío");
        }

        BigDecimal tasa = tasasCambio.get(moneda.toUpperCase());

        if (tasa == null) {
            throw new IllegalArgumentException(
                    "Moneda no soportada: " + moneda + ". " +
                            "Monedas disponibles: CNY, PEN, USD, EUR"
            );
        }

        return tasa;
    }

    public BigDecimal convertirMoneda(BigDecimal monto, String monedaOrigen, String monedaDestino) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto debe ser mayor o igual a cero");
        }

        if (monedaOrigen.equalsIgnoreCase(monedaDestino)) {
            return monto;
        }

        BigDecimal tasaOrigen = obtenerTasaCambio(monedaOrigen);
        BigDecimal tasaDestino = obtenerTasaCambio(monedaDestino);

        BigDecimal resultado = monto
                .multiply(tasaOrigen)
                .divide(tasaDestino, 2, RoundingMode.HALF_UP);

        return resultado;
    }

    public BigDecimal convertirAMonedaCorporativa(BigDecimal monto, String monedaOrigen) {
        return convertirMoneda(monto, monedaOrigen, monedaCorporativa);
    }

    public void actualizarTasaCambio(String moneda, BigDecimal nuevaTasa) {
        if (moneda == null || moneda.isEmpty()) {
            throw new IllegalArgumentException("El código de moneda no puede ser nulo o vacío");
        }

        if (nuevaTasa == null || nuevaTasa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasa de cambio debe ser mayor a cero");
        }

        tasasCambio.put(moneda.toUpperCase(), nuevaTasa);
    }

    public Map<String, BigDecimal> obtenerTodasLasTasas() {
        return new HashMap<>(tasasCambio); // Retornar copia para evitar modificaciones externas
    }

    public boolean esMonedaSoportada(String moneda) {
        return moneda != null && tasasCambio.containsKey(moneda.toUpperCase());
    }
}
