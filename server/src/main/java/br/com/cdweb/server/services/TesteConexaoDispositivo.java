package br.com.cdweb.server.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.cdweb.conexao.WebTesteMessage;

@Path("testeConexao")
public class TesteConexaoDispositivo {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public WebTesteMessage realizarAcao(@PathParam("id") long id, @Context HttpServletRequest req) {
		return new WebTesteMessage("CDWEB_WEB_SERVER");
    }
}