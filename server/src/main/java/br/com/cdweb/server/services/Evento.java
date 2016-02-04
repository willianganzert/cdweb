package br.com.cdweb.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;

import br.com.cdweb.mensagens.StatusMensagem;
import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;


@Path("evento")
public class Evento {
	@Resource
	WebServiceContext wsContext;
	@Context 
	private HttpServletRequest hsr;
	

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<FilaEventoExecutar> getEventos() {
		List<FilaEventoExecutar> filaEventoExecutars = new ArrayList<FilaEventoExecutar>();
		ResultFilterVo<FilaEventoExecutar> resultFilterVo = JpaAllEntities.doFilter(FilaEventoExecutar.class, new FieldValuesVo("status", StatusMensagem.pendente()));
		
		if(resultFilterVo.getResultQuery() != null && resultFilterVo.getResultQuery().size() > 0){
			for (FilaEventoExecutar filaEventoExecutar : resultFilterVo.getResultQuery()) {
				filaEventoExecutars.add((FilaEventoExecutar) filaEventoExecutar.clone());
				JpaAllEntities.merge(filaEventoExecutar);
				filaEventoExecutar.setStatus(StatusMensagem.processada());
			}
			JpaAllEntities.update(resultFilterVo.getResultQuery().toArray(new FilaEventoExecutar[0]));
		}

		return filaEventoExecutars;
	}

}