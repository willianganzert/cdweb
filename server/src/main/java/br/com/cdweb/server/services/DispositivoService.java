package br.com.cdweb.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import br.com.cdweb.persistence.domain.Dispositivo;
import br.com.cdweb.persistence.domain.Parametro;
import br.com.cdweb.persistence.domain.Usuario;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.type.OrderType;
import br.com.cdweb.persistence.util.UtlEntity;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.server.filters.Secured;
import br.com.cdweb.server.util.TokenUtil;

@Path("dispositivos")
public class DispositivoService extends TemplateCRUDService<Dispositivo>{

  
	
	public DispositivoService() {
		this(Dispositivo.class);
	}
	public DispositivoService(Class<Dispositivo> type) {
		super(type);
	}
	
	
	@POST
	@Path("/{id}/parametros")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public List<Parametro> buscarParametrosDispositivo(@PathParam("id") long id) {
		Dispositivo dispositivo = new Dispositivo();
		dispositivo.setIdDispositivo(id);
		ResultFilterVo<Parametro> resultFilterVo = JpaAllEntities.doFilter(null, null, Parametro.class, new FieldValuesVo("dispositivo", dispositivo));
		return resultFilterVo.getResultQuery();
    }

}