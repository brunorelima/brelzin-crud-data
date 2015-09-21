package br.com.brelzin.crudteste.modelo;

// Generated 20/09/2015 22:11:09 by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Pessoa generated by hbm2java
 */
@Entity
@Table(name = "pessoa", catalog = "brelzindb_teste")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonInclude(Include.NON_NULL)
public class Pessoa implements java.io.Serializable {

	private Integer id;
	private Unidade unidade;
	private String nome;
	private String usuario;
	private String senha;
	private Date nascimento;

	public Pessoa() {
	}

	public Pessoa(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Pessoa(Unidade unidade, String nome, String usuario, String senha) {
		this.unidade = unidade;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Pessoa(Unidade unidade, String nome, String usuario, String senha, Date nascimento) {
		this.unidade = unidade;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.nascimento = nascimento;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidade_fk_id", nullable = false)
	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Column(name = "nome", nullable = false, length = 25)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "usuario", nullable = false, length = 10)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "senha", nullable = false, length = 25)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm a z")
//	@Temporal(TemporalType.DATE)
	@Column(name = "nascimento", length = 10)
	public Date getNascimento() {
		return this.nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

}