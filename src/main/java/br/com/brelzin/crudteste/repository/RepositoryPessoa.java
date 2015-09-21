package br.com.brelzin.crudteste.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import br.com.brelzin.crudteste.modelo.Pessoa;

public interface RepositoryPessoa extends Repository<Pessoa, Integer> {

	Page<Pessoa> findAll(Pageable pageable);
	
}
