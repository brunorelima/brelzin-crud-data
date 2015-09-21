package br.com.brelzin.crudteste.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.brelzin.crudteste.modelo.Pessoa;

@Transactional
@Repository
public class RepositoryPessoaJdbc {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int countListaPessoas() {
		return this.jdbcTemplate.queryForObject("select count(*) from pessoa", Integer.class);
	}

	public Pessoa login(String usuario, String senha) {
		String sql = "SELECT * FROM pessoa  WHERE usuario = ? and senha = ? ";
		List<Pessoa> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Pessoa.class), usuario, senha);
		return (list.size() > 0) ? list.get(0) : null;
	}

	public List<Pessoa> listarUsuariosComNomeIgual(String usuario) {
		String sql = "SELECT * FROM pessoa  WHERE usuario like '" + usuario + "'";

		List<Pessoa> list = jdbcTemplate.query(sql, new RowMapper<Pessoa>() {
			public Pessoa mapRow(ResultSet result, int rowNum) throws SQLException {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(result.getInt("id"));
				pessoa.setUsuario(result.getString("usuario"));
				return pessoa;
			}
		});

		return list;
	}

}
