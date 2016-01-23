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
@Table(name="area")
@SequenceGenerator(name="area_id_area_seq", sequenceName="area_id_area_seq", allocationSize=1)
public class Area  extends ComunEntidades implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2383542454530985998L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="area_id_area_seq")
    @Column(name = "id_area", updatable=false)
	private long idArea;

	private String nome;
	
	public Area() {
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
