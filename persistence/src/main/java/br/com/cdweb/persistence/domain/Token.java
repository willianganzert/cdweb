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
@Table(name="token")
@SequenceGenerator(name="token_id_token_seq", sequenceName="token_id_token_seq", allocationSize=1)
public class Token  extends ComunEntidades implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="token_id_token_seq")
    @Column(name = "id_token", updatable=false)
	private long idToken;

	private String generatedToken;
	
	@ManyToOne()
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaCriacao;
	
	public Token() {
	}

	public long getIdToken() {
		return idToken;
	}

	public void setIdToken(long idToken) {
		this.idToken = idToken;
	}

	public String getGeneratedToken() {
		return generatedToken;
	}

	public void setGeneratedToken(String generatedToken) {
		this.generatedToken = generatedToken;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(Date horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	

}
