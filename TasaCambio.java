package conversor;

import java.util.Map;

public class TasaCambio {
    private String base;
    private Map<String, Double> rates;

    // Getters y Setters
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}