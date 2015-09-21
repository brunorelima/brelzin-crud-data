package br.com.brelzin.crudteste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brelzin.crudteste.modelo.Pessoa;
import br.com.brelzin.crudteste.repository.RepositoryPessoaJdbc;
import br.com.brelzin.crudteste.repository.RepositoryPessoaJpa;

@RestController
@RequestMapping("/pessoa")
public class PessoaDaoImpController {
	
	@Autowired
	private RepositoryPessoaJdbc repositoryJdbc;
	
	@Autowired
	private RepositoryPessoaJpa repositoryJpa;
	

	@RequestMapping("/exemploPessoaJdbc")
	public List<Pessoa> exemploPessoaJdbc() {

//		int countListaUsuarios = repository.countListaUsuarios();
//		Usuario usuario = repository.login("bruno", "teste123");
		List<Pessoa> list = repositoryJdbc.listarUsuariosComNomeIgual("bruno");

		return list;
	}
	
	@RequestMapping("/exemploPessoaJpa")
	public List<Pessoa> exemploPessoaJpa() {
		List<Pessoa> list = repositoryJpa.listarUsuarios("bruno", 1);
		return list;
	}
	
	@RequestMapping("/exemploPessoaJpa2")
	public List<Pessoa> exemploPessoaJpa2() {
		return repositoryJpa.listaComSelectFiltrado();
	}
	
	@RequestMapping("/exemploPessoaJpa3")
	public List<Pessoa> exemploPessoaJpa3() {
		return repositoryJpa.listandoUsandoQueryNativa();
	}
	

}
