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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


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
//	@XmlAttribute @XmlID
	private long idModeloDispositivo;

	@ManyToOne
	@JoinColumn(name="id_dispositivo")
	private Dispositivo dispositivo;
	
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area area;
	
	@Column(name = "nome")
	private String nome;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_modelo_dispositivo")
	@JsonManagedReference
    private List<ModeloAcao> modeloAcoes;
	
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


	public List<ModeloAcao> getModeloAcoes() {
		return modeloAcoes;
	}


	public void setModeloAcoes(List<ModeloAcao> modeloAcoes) {
		this.modeloAcoes = modeloAcoes;
	}

}
