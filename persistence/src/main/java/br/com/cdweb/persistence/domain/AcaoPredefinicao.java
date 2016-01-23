package br.com.cdweb.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	
	public AcaoPredefinicao() {
	}
	
	public long getIdAcaoPredeficao() {
		return idAcaoPredeficao;
	}
	public void setIdAcaoPredeficao(long idAcaoPredeficao) {
		this.idAcaoPredeficao = idAcaoPredeficao;
	}

}
