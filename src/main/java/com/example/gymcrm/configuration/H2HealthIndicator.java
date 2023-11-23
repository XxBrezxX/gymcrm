package com.example.gymcrm.configuration;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class H2HealthIndicator implements HealthIndicator {

    private DataSource dataSource;

    public H2HealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(1)) {
                return Health.up().withDetail("mensaje", "Conexión exitosa a H2").build();
            } else {
                return Health.down().withDetail("mensaje", "No se puede conectar a H2").build();
            }
        } catch (Exception e) {
            return Health.down().withDetail("mensaje", "Error al conectar a H2: " + e.getMessage()).build();
        }
    }
}