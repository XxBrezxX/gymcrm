package com.example.gymcrm.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class H2HealthIndicatorTest {

    private DataSource dataSource = mock(DataSource.class);
    private Connection connection = mock(Connection.class);

    @Test
    void testH2HealthIndicatorUp() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.isValid(1)).thenReturn(true);

        H2HealthIndicator healthIndicator = new H2HealthIndicator(dataSource);
        Health health = healthIndicator.health();

        assertNotNull(health);
        assertEquals("UP", health.getStatus().getCode());
        assertEquals("Conexión exitosa a H2", health.getDetails().get("mensaje"));
    }

    @Test
    void testH2HealthIndicatorDown() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.isValid(1)).thenReturn(false);

        H2HealthIndicator healthIndicator = new H2HealthIndicator(dataSource);
        Health health = healthIndicator.health();

        assertNotNull(health);
        assertEquals("DOWN", health.getStatus().getCode());
        assertEquals("No se puede conectar a H2", health.getDetails().get("mensaje"));
    }

    @Test
    public void testHealthDown() throws SQLException {
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        SQLException exception = new SQLException("Error");
        String message = "mensaje";

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.isValid(1)).thenThrow(exception);

        H2HealthIndicator healthIndicator = new H2HealthIndicator(mockDataSource);
        Health health = healthIndicator.health();
        mockConnection.close();

        assertEquals("DOWN", health.getStatus().getCode());
        assertEquals("Error al conectar a H2: Error", health.getDetails().get(message));
    }
}