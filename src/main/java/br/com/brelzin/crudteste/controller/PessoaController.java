package br.com.brelzin.crudteste.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brelzin.crudteste.modelo.Pessoa;

@RestController
public class PessoaController {
	
	@RequestMapping("/helloworld")
	public String helloworld() {
		return "Hello world";
	}

	@RequestMapping("/helloworldObjeto")
	public Pessoa helloworldObjeto() {
		return new Pessoa(0, "Hello World");
	}
	
	@RequestMapping("/helloname")
	public Pessoa hello(@RequestParam(value = "nome", defaultValue = "World") String nome) {
		return new Pessoa(0, "Hello "+ nome + "!");
	}

	@RequestMapping("/hello/{nome}")
	public Pessoa helloname(@PathVariable String nome) {
		return new Pessoa(0, "Hello "+ nome + "!!!");
	}
	
	@RequestMapping(value = "/hello2/{nome}", method = RequestMethod.POST)
	public Pessoa helloname2(@PathVariable String nome) {
		return new Pessoa(0, "Hello "+ nome + "!!!!!!");
	}
	
	@RequestMapping("/hellonameSetSessao")
	public String hellonameSetSessao(@RequestParam(value = "nome", defaultValue = "Padrao") String nome, HttpSession httpSession) {
		httpSession.setAttribute("testeSessao", nome);
		return "Valor salvo na sessão: " + nome;
	}
	
	@RequestMapping("/hellonameGetSessao")
	public String hellonameGetSessao(HttpSession httpSession) {
		return "Valor da sessão: " + httpSession.getAttribute("testeSessao").toString();		
	}
	
}
