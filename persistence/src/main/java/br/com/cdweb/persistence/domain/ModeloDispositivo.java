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
@Table(name="modelo_dispositivo")
@SequenceGenerator(name="modelo_dispositivo_id_modelo_dispositivo_seq", sequenceName="modelo_dispositivo_id_modelo_dispositivo_seq",
allocationSize=1)
public class ModeloDispositivo extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5072581408493829746L;
	
	@Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="modelo_dispositivo_id_modelo_dispositivo_seq")	
    @Column(name = "id_modelo_dispositivo", updatable=false)
	private long idModeloDispositivo;

	@ManyToOne
	@JoinColumn(name="id_dispositivo")
	private Dispositivo dispositivo;
	
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area area;
	
	private String nome;

	
	public ModeloDispositivo() {
	
	}
	
	
	public long getIdModeloDispositivo() {
		return idModeloDispositivo;
	}

	public void setIdModeloDispositivo(long idModeloDispositivo) {
		this.idModeloDispositivo = idModeloDispositivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	

}
