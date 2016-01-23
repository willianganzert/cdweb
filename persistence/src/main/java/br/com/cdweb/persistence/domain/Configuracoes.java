package br.com.cdweb.persistence.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Configuracoes
 *
 */
@Entity
@Table(name="configuracoes")
public class Configuracoes extends ComunEntidades implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String chave;
	private String valor;
	
	
	public Configuracoes() {
		super();
	}
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
   
}
