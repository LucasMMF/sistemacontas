package br.com.cotiinformatica.repositories;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ContaRepository {

	// Atributo
	private JdbcTemplate jdbcTemplate;

	// Método construtor
	public ContaRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
