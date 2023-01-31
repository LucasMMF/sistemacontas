package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Usuario;

public class UsuarioRepository {

	// Atributo
	private JdbcTemplate jdbcTemplate;

	// Método construtor
	public UsuarioRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void create(Usuario usuario) throws Exception {

		String query = "INSERT INTO usuario(nome, email, senha) VALUES(?, ?, md5(?))";
		Object[] params = { usuario.getNome(), usuario.getEmail(), usuario.getSenha() };

		jdbcTemplate.update(query, params);
	}

	public Usuario findByEmail(String email) throws Exception {

		String query = "SELECT * FROM usuario WHERE email = ?";
		Object[] params = { email };

		List<Usuario> lista = jdbcTemplate.query(query, params, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			}
		});

		if (lista.size() == 1) // Verificando se 1 usuário foi encontrado
			return lista.get(0); // Retornando os dados do usuário encontrado
		else
			return null; // Retornando vazio
	}
}
