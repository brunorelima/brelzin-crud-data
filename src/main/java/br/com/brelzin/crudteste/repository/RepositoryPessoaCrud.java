package br.com.brelzin.crudteste.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.brelzin.crudteste.modelo.Pessoa;

@Transactional
public interface RepositoryPessoaCrud extends CrudRepository<Pessoa, Integer> {

	public List<Pessoa> findByUsuario(String usuario);	
	public List<Pessoa> findByUsuarioIgnoreCaseContaining(String usuario);
	public Pessoa findByIdAndUsuario(Integer id, String usuario);

}
