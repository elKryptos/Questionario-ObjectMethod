package it.objectmethod.questionario2024.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseIntegrationTest extends BaseIntegrationtest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate; // JdbcTemplate ti permette di eseguire query di test

    @Test
    public void contextLoads() {
        // Verifica che il contesto si carica correttamente
        assertThat(context).isNotNull();
    }

    @Test
    public void testH2DatabaseConnection() {
        // Verifica che la connessione al database H2 funzioni correttamente
        Integer result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'UTENTE'", Integer.class);
        assertThat(result).isEqualTo(1); // Verifica che la tabella esista
    }
}