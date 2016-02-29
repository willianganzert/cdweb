package br.com.cdweb.server.services;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cdweb.mensagens.StatusMensagem;
import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.persistence.domain.ModeloAcao;
import br.com.cdweb.persistence.domain.Usuario;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.server.filters.Secured;
import br.com.cdweb.server.startup.ControlAccessBean;
import br.com.cdweb.server.util.TokenUtil;

@ManagedBean
@ApplicationScoped
@Path("acao")
public class AcaoService {

	private ControlAccessBean bean = new ControlAccessBean();
	
//	@Secured
	@POST
	@Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StatusRequisicao realizarAcao(@PathParam("id") long id, @Context HttpServletRequest req) {		
		StatusRequisicao requisicao = new StatusRequisicao();
//		ModeloAcao modeloAcao = JpaAllEntities.findById(id, ModeloAcao.class);
		
//		Response.Status requisicao2 = new TokenUtil().validateTokenAccessAction(req.getHeader(HttpHeaders.WWW_AUTHENTICATE), id);
				
//		if(requisicao.equals(StatusRequisicao.Status.OK)){
			Usuario usuario = getUsuarioSessao(req);
			FilaEventoExecutar eventoExecutar = new FilaEventoExecutar();
			eventoExecutar.setModeloAcao(JpaAllEntities.findById(id, ModeloAcao.class));
			eventoExecutar.setStatus(StatusMensagem.pendente());
			eventoExecutar.setUsuario(usuario);
			JpaAllEntities.insert(eventoExecutar);
			requisicao.setStatusOK();
//		}
				
        return requisicao;
    }

	private Usuario getUsuarioSessao(HttpServletRequest req) {
		return JpaAllEntities.findById(1, Usuario.class);
	}

}