package br.com.cdweb.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="usuario")
@SequenceGenerator(name="usuario_id_usuario_seq", sequenceName="usuario_id_usuario_seq", allocationSize=1)	
@XmlRootElement
public class Usuario  extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2972313420767777806L;

	@Id    
    @GeneratedValue(generator="usuario_id_usuario_seq")	
    @Column(name = "id_usuario", updatable=false)
	private long idUsuario;

	private String login;

	private String senha;
	
	@Column(length=100)
	private String nome;
	
	@Column(length=14)
	private String telefone;

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
