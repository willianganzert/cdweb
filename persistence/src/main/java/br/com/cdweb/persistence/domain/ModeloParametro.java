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
@Table(name="modelo_parametro")
@SequenceGenerator(name="modelo_parametro_id_modelo_parametro_seq", sequenceName="modelo_parametro_id_modelo_parametro_seq", allocationSize=1)
public class ModeloParametro extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5360076284629466609L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="modelo_parametro_id_modelo_parametro_seq")	
    @Column(name = "id_modelo_parametro", updatable=false)
	private long idModeloParametro;

	@ManyToOne()
	@JoinColumn(name="id_parametro")
	private Parametro parametro;
	
	@ManyToOne
	@JoinColumn(name="id_modelo_acao")
	private ModeloAcao modeloAcao;
	
	private String valorParametroAcao;
	
	
	public ModeloParametro() {
	}
	

	public long getIdModeloParametro() {
		return idModeloParametro;
	}

	public void setIdModeloParametro(long idModeloParametro) {
		this.idModeloParametro = idModeloParametro;
	}

	public String getValorParametroAcao() {
		return valorParametroAcao;
	}

	public void setValorParametroAcao(String valorParametroAcao) {
		this.valorParametroAcao = valorParametroAcao;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public ModeloAcao getModeloAcao() {
		return modeloAcao;
	}

	public void setModeloAcao(ModeloAcao modeloAcao) {
		this.modeloAcao = modeloAcao;
	}

}
