package br.com.cdweb.server.services;

import javax.ws.rs.Path;

import br.com.cdweb.persistence.domain.Usuario;

@Path("usuarios")
public class UsuarioService extends TemplateCRUDService<Usuario>{

	public UsuarioService() {
		this(Usuario.class);
	}
	
	public UsuarioService(Class<Usuario> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}