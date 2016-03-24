package br.com.cdweb.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.com.cdweb.persistence.domain.ModeloDispositivo;
import br.com.cdweb.persistence.domain.PerfilAcesso;
import br.com.cdweb.persistence.domain.Usuario;
import br.com.cdweb.persistence.domain.UsuarioPerfil;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.server.filters.Secured;
import br.com.cdweb.server.util.TokenUtil;

@Path("modelosdispositivo")
public class ModeloDispositivoService extends TemplateCRUDService<ModeloDispositivo> {

	public ModeloDispositivoService() {
		this(ModeloDispositivo.class);
	}

	public ModeloDispositivoService(Class<ModeloDispositivo> type) {
		super(type);
	}

	@Context
	private HttpServletRequest httpRequest;

	@Override
	@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ModeloDispositivo> buscarTodos() {
		String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		TokenUtil tk = new TokenUtil();
		Usuario usuario;

		List<ModeloDispositivo> listDispo = new ArrayList<>();
		try {
			usuario = tk.getUsuario(authorizationHeader);


			ResultFilterVo<UsuarioPerfil> resultUserPerfil = JpaAllEntities.doFilter(UsuarioPerfil.class,
					new FieldValuesVo("usuario", usuario));
			if (resultUserPerfil != null && resultUserPerfil.getResultQuery() != null
					&& resultUserPerfil.getResultQuery().size() > 0) {
				List<UsuarioPerfil> usuarioPerfils = resultUserPerfil.getResultQuery();
				boolean hasAdmAccess = false;
				for (UsuarioPerfil usuarioPerfil : usuarioPerfils) {
					if (usuarioPerfil.getPerfil() != null && usuarioPerfil.getPerfil().getNome().toLowerCase().equals("administrador")) {
						hasAdmAccess = true;
						break;
					}
					for (PerfilAcesso perfilAcesso : usuarioPerfil.getPerfil().getPerfisAcesso()) {
						listDispo.add(perfilAcesso.getModeloDispositivo());
					}

				}
				if(hasAdmAccess){
					listDispo.addAll(JpaAllEntities.listAll(null, null, ModeloDispositivo.class));
				}
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listDispo;
	}
}