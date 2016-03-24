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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="modelo_acao")
@SequenceGenerator(name="modelo_acao_id_modelo_acao_seq", sequenceName="modelo_acao_id_modelo_acao_seq", allocationSize=1)
public class ModeloAcao extends ComunEntidades implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6504996157538844876L;
	
	@Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="modelo_acao_id_modelo_acao_seq")
    @Column(name = "id_modelo_acao", updatable=false)
	private long idModeloAcao;
	
	@ManyToOne()
	@JoinColumn(name = "id_modelo_dispositivo")
	@JsonBackReference
//	@XmlAttribute @XmlIDREF 
	private ModeloDispositivo modeloDispositivo;

	private String nome;

	private String descricao;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_modelo_acao")
	@JsonManagedReference
    private List<ModeloParametro> modeloParametros;
	
	
	public ModeloAcao() {
	
	}
	
	public long getIdModeloAcao() {
		return idModeloAcao;
	}

	public void setIdModeloAcao(long idModeloAcao) {
		this.idModeloAcao = idModeloAcao;
	}

	public ModeloDispositivo getModeloDispositivo() {
		return modeloDispositivo;
	}

	public void setModeloDispositivo(ModeloDispositivo modeloDispositivo) {
		this.modeloDispositivo = modeloDispositivo;
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

	public List<ModeloParametro> getModeloParametros() {
		return modeloParametros;
	}

	public void setModeloParametros(List<ModeloParametro> modeloParametros) {
		this.modeloParametros = modeloParametros;
	}

	

	

}
