package br.com.cdweb.persistence.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="perfil_acesso")
@SequenceGenerator(name="perfil_acesso_id_perfil_acesso_seq", sequenceName="perfil_acesso_id_perfil_acesso_seq", allocationSize=1)
public class PerfilAcesso  extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 544226908245284451L;
	
	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="perfil_acesso_id_perfil_acesso_seq")	
    @Column(name = "id_perfil_acesso", updatable=false)
	private long idPerfilAcesso;
	
	@JoinColumn(name="id_perfil")
	private Perfil perfil;
	
	@JoinColumn(name="id_modelo_predefinicao")
	private ModeloPredefinicao modeloPredefinicao;
	
	
	@JoinColumn(name="id_modelo_dispositivo")
	private ModeloDispositivo modeloDispositivo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtribuicao;
	
	
	
	public PerfilAcesso() {
	
	}
	
	
	public long getIdPerfilAcesso() {
		return idPerfilAcesso;
	}

	public void setIdPerfilAcesso(long idPerfilAcesso) {
		this.idPerfilAcesso = idPerfilAcesso;
	}
	
	public Date getDataAtribuicao() {
		return dataAtribuicao;
	}

	public void setDataAtribuicao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}


	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


	public ModeloPredefinicao getModeloPredefinicao() {
		return modeloPredefinicao;
	}


	public void setModeloPredefinicao(ModeloPredefinicao modeloPredefinicao) {
		this.modeloPredefinicao = modeloPredefinicao;
	}


	public ModeloDispositivo getModeloDispositivo() {
		return modeloDispositivo;
	}


	public void setModeloDispositivo(ModeloDispositivo modeloDispositivo) {
		this.modeloDispositivo = modeloDispositivo;
	}


}
