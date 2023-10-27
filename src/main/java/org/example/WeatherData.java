package org.example;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;
public class WeatherData {

    @NotNull
    private String city;

    @NotNull
    @DecimalMin(value = "-50.0", inclusive = true, message = "Temperature must be at least -50")
    @DecimalMax(value = "50.0", inclusive = true, message = "Temperature must be at most 50")
    private BigDecimal temperature;

    @NotNull
    private String condition;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
