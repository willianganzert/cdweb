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

@Entity
@Table(name="perfil")
@SequenceGenerator(name="perfil_id_perfil_seq", sequenceName="perfil_id_perfil_seq", allocationSize=1)
public class Perfil  extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1131850648956130365L;

	@Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="perfil_id_perfil_seq")
    @Column(name = "id_perfil", updatable=false)
	private long idPerfil;
	
	private String nome;

	private String descricao;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="id_perfil")
	private List<PerfilAcesso> perfisAcesso;
	
	
	public Perfil() {
	}
	public Perfil(long idPerfil) {
		setIdPerfil(idPerfil);
	}
	
	public long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
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


	public List<PerfilAcesso> getPerfisAcesso() {
		return perfisAcesso;
	}


	public void setPerfisAcesso(List<PerfilAcesso> perfisAcesso) {
		this.perfisAcesso = perfisAcesso;
	}

	

}
