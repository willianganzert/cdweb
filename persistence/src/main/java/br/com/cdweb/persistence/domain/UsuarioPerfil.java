package br.com.cdweb.persistence.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="usuario_perfil")
@SequenceGenerator(name="usuario_perfil_id_usuario_perfil_seq", sequenceName="usuario_perfil_id_usuario_perfil_seq", allocationSize=1)
public class UsuarioPerfil  extends ComunEntidades implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4802938299145735748L;
	
	@Id    	
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="usuario_perfil_id_usuario_perfil_seq")	
    @Column(name = "id_usuario_perfil", updatable=false)
	private long idUsuarioPerfil;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtribuicao;

	public long getIdUsuarioPerfil() {
		return idUsuarioPerfil;
	}

	public void setIdUsuarioPerfil(long idUsuarioPerfil) {
		this.idUsuarioPerfil = idUsuarioPerfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Date getDataAtribuicao() {
		return dataAtribuicao;
	}

	public void setDataAtribuicao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}
	
	
	
}
