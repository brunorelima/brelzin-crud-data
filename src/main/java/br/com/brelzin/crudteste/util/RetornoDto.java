package br.com.brelzin.crudteste.util;

import java.util.HashMap;
import java.util.Map;

public class RetornoDto<T> {
	public enum StatusRetorno { ok, erro};
	public enum  CodigoErro { INDEFINIDO, COMUM, SESSAO_EXPIRADA, CANCELADO }
	
//	private String status;
	private StatusRetorno status;
	private CodigoErro codigoErro = CodigoErro.INDEFINIDO;
	private String msg;
	private T obj;
	private String url;
	private Map<String, Object> aux;

	public RetornoDto() {
	}

	// Construtor de erro
	public RetornoDto(String msg) {
		this.msg = msg;
		this.status = StatusRetorno.erro;
	}

	// Construtor de ok
	public RetornoDto(T obj) {
		this.obj = obj;

		if (obj != null) {
			this.status = StatusRetorno.ok;
		} else {
			this.status = StatusRetorno.erro;
			this.msg = "Objeto recebido esta vazio!";
		}
	}
	
	public RetornoDto(CodigoErro codigoErro) {
		this.status = StatusRetorno.erro;
		this.codigoErro = codigoErro;
		this.msg = getMsgPadraoErro();
	}
	

//	@JsonProperty("status")
	public StatusRetorno getStatus() {
		return status;
	}

	public void setStatus(StatusRetorno status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "RetornoIFTM [status=" + status + ", mensagem=" + msg + ", obj=" + obj + ", url=" + url + ", aux=" + aux + "]";
	}

	public Map<String, Object> getAux() {
		return aux;
	}

	public void setAux(Map<String, Object> aux) {
		this.aux = aux;
	}
	
	public void addAux(String chave, Object valor) {
		this.aux = (this.aux == null) ? new HashMap<String, Object>() : this.aux;
		this.aux.put(chave, valor);
	}
	
	private String getMsgPadraoErro(){
		switch (codigoErro) {
		case CANCELADO:
			return "Cancelado";

		case COMUM:
			return "Erro indefinido";

		case SESSAO_EXPIRADA:
			return "Sessão expirada";

		case INDEFINIDO:
			return "Indefinido";

		default:
			return "Indefinido";
		}
	}


}
