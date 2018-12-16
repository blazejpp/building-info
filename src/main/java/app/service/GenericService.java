package app.service;

import app.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface GenericService<T extends Model> {

    JpaRepository<T, Long> getRepository();

    BigDecimal calculateArea(T t);

    BigDecimal calculateVolume(T t);

    BigDecimal calculateLighting(T t);

    BigDecimal calculateLightingPerArea(T t);

    BigDecimal calculateHeating(T t);

    BigDecimal calculateHeatingPerVolume(T t);

}
