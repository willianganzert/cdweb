package br.com.cdweb.server.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import br.com.cdweb.persistence.domain.Perfil;

@Path("perfis")
public class PerfilService extends TemplateCRUDService<Perfil>{

	@Context
	private HttpServletRequest httpRequest;
	
	public PerfilService() {
		this(Perfil.class);
	}
	
	
	public PerfilService(Class<Perfil> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
}