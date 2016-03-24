package br.com.cdweb.persistence.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="dispositivo")
@SequenceGenerator(name="dispositivo_id_dispositivo_seq", sequenceName="dispositivo_id_dispositivo_seq", allocationSize=1)
public class Dispositivo extends ComunEntidades implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5313126163246103312L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="dispositivo_id_dispositivo_seq")	
    @Column(name = "id_dispositivo", updatable=false)
	private long idDispositivo;

	private String nome;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="dispositivo")
	private List<Parametro> parametros;
	
	public Dispositivo() {
		// TODO Auto-generated constructor stub
	}
	
	public Dispositivo(long idDispositivo) {
		super();
		this.idDispositivo = idDispositivo;
	}


	public long getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

}
