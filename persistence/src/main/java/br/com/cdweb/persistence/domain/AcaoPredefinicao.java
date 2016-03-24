package br.com.cdweb.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="acao_predefinicao")
@SequenceGenerator(name="acao_predefinicao_id_acao_predefinicao_seq",
sequenceName="acao_predefinicao_id_acao_predefinicao_seq",
allocationSize=1)
public class AcaoPredefinicao extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id    
    @GeneratedValue(generator="acao_predefinicao_id_acao_predefinicao_seq")
    @Column(name = "id_acao_predeficao", updatable=false)
	private long idAcaoPredeficao;
	
	@ManyToOne
	@JoinColumn(name="id_modelo_acao")
	@JsonBackReference
	private ModeloAcao modeloAcao;
	
	@ManyToOne
	@JoinColumn(name="id_modelo_predefinicao")
	private ModeloPredefinicao modeloPredefinicao;
	
	public AcaoPredefinicao() {
	}
	
	public long getIdAcaoPredeficao() {
		return idAcaoPredeficao;
	}
	public void setIdAcaoPredeficao(long idAcaoPredeficao) {
		this.idAcaoPredeficao = idAcaoPredeficao;
	}

}
