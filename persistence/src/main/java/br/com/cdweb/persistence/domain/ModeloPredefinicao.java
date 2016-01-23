package br.com.cdweb.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="modelo_predefinicao")
@SequenceGenerator(name="modelo_predefinicao_id_modelo_predefinicao_seq", sequenceName="modelo_predefinicao_id_modelo_predefinicao_seq", allocationSize=1)
public class ModeloPredefinicao  extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7084921622387297614L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="modelo_predefinicao_id_modelo_predefinicao_seq")	
    @Column(name = "id_modelo_parametro", updatable=false)
	private long idModeloPredefinicao;

	private String nome;

	private String descricao;
	
	
	public ModeloPredefinicao() {
	}
	

	public long getIdModeloPredefinicao() {
		return idModeloPredefinicao;
	}

	public void setIdModeloPredefinicao(long idModeloPredefinicao) {
		this.idModeloPredefinicao = idModeloPredefinicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
