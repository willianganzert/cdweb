package br.com.cdweb.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="parametro")
@SequenceGenerator(name="parametro_id_parametro_seq",
sequenceName="parametro_id_parametro_seq", allocationSize=1)	
public class Parametro  extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3225155586814133708L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="parametro_id_parametro_seq")	
    @Column(name = "id_parametro", updatable=false)
	private long idParametro;
	
	@ManyToOne
	@JoinColumn(name="id_dispositivo")
	private Dispositivo dispositivo;
	
	private String nome;

	/**
	 * TIPO 1 = TEXTO
	 * TIPO 2 = NUMERICO
	 * TIPO 3 = INTERVALO
	 * 
	 */
	private int tipo;

	private String tipoValor;

	private String tipoValor2;
	
	
	
	public Parametro() {
	
	}	
	

	public long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getTipoValor2() {
		return tipoValor2;
	}

	public void setTipoValor2(String tipoValor2) {
		this.tipoValor2 = tipoValor2;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	
}
