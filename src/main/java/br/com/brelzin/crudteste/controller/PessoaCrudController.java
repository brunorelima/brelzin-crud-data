package br.com.brelzin.crudteste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brelzin.crudteste.modelo.Pessoa;
import br.com.brelzin.crudteste.repository.RepositoryPessoaCrud;

@RestController 
@RequestMapping("/pessoa")
public class PessoaCrudController {

	@Autowired
	private RepositoryPessoaCrud repository;
	
	@RequestMapping("/findAll")
	public Iterable<Pessoa> findAll() {
		return repository.findAll();
	}
	
	@RequestMapping("/findById/{id}")
	public Pessoa findById(@PathVariable int id) {
		return repository.findOne(id);
	}
	
	@RequestMapping("/findByUsuario/{usuario}")
	public List<Pessoa> findByUsuario(@PathVariable String usuario) {
		return repository.findByUsuario(usuario);
	}
	
	@RequestMapping("/findByUsuarioIgnoreCaseContaining/{usuario}")
	public List<Pessoa> findByUsuarioIgnoreCaseContaining(@PathVariable String usuario) {
		return repository.findByUsuarioIgnoreCaseContaining(usuario);
	}
	
	@RequestMapping("/findByIdAndUsuario/{usuario}")
	public Pessoa findByIdAndUsuario(@PathVariable Integer id, @PathVariable String usuario) {
		return repository.findByIdAndUsuario(id, usuario);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public Pessoa save(@RequestParam(value = "nome", defaultValue = "Vazio") String nome) {
		Pessoa pessoa = new Pessoa(0, nome);		
		pessoa = repository.save(pessoa);
		return pessoa;
	}
	
}
