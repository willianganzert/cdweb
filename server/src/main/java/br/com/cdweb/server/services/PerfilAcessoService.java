package br.com.cdweb.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.cdweb.persistence.domain.ModeloDispositivo;
import br.com.cdweb.persistence.domain.Perfil;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.server.filters.Secured;

@Path("perfisacesso")
public class PerfilAcessoService {

	@Context
	private HttpServletRequest httpRequest;
	
	

	@Secured
    @GET
    @Path("/perfil/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Long[] buscarIdsModelosDispositivosByPerfil(@PathParam("id") long id) {
		List<Long> idsModelosDispositivos = new ArrayList<>();
		Perfil perfil = JpaAllEntities.findById(id, Perfil.class);
		ResultFilterVo<br.com.cdweb.persistence.domain.PerfilAcesso> resultFilterVo = JpaAllEntities.doFilter(br.com.cdweb.persistence.domain.PerfilAcesso.class, new FieldValuesVo("perfil", perfil));
		if(resultFilterVo != null && !resultFilterVo.getResultQuery().isEmpty()){
			for (br.com.cdweb.persistence.domain.PerfilAcesso perfilAcesso: resultFilterVo.getResultQuery()) {
				idsModelosDispositivos.add(perfilAcesso.getModeloDispositivo().getIdModeloDispositivo());
			}
			
		}
		
        return idsModelosDispositivos.toArray(new Long[0]);
    }
	
	@Secured
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void atualizar(@PathParam("id") long idPerfil, Long[] ids) {
        Long[] idsAtuais = buscarIdsModelosDispositivosByPerfil(idPerfil);
        List<Long> idsRemover = new ArrayList<>();
        List<Long> idsAdicionar = new ArrayList<>();
        for (int i = 0; i < idsAtuais.length; i++) {
        	boolean encontrado = false;
			for (int j = 0; j < ids.length; j++) {
				if(ids[j] == idsAtuais[i]){
					encontrado = true;break;
				}
			}
			if(!encontrado){
				idsRemover.add(idsAtuais[i]);
			}
		}
        for (int i = 0; i < ids.length; i++) {
        	boolean encontrado = false;
			for (int j = 0; j < idsAtuais.length; j++) {
				if(ids[i] == idsAtuais[j]){
					encontrado = true;break;
				}
			}
			if(!encontrado){
				idsAdicionar.add(ids[i]);
			}
		}
        if(idsAdicionar.size() > 0){
        	List<br.com.cdweb.persistence.domain.PerfilAcesso> perfisAcesso = new ArrayList<>();
        	for (Long long1 : idsAdicionar) {
        		br.com.cdweb.persistence.domain.PerfilAcesso perfilAcesso =  new br.com.cdweb.persistence.domain.PerfilAcesso();
        		perfilAcesso.setDataAtribuicao(new Date());
        		perfilAcesso.setPerfil(new Perfil(idPerfil));
        		perfilAcesso.setModeloDispositivo(new ModeloDispositivo(long1));
        		        		
        		perfisAcesso.add(perfilAcesso);
			}
        	JpaAllEntities.insert(perfisAcesso.toArray(new br.com.cdweb.persistence.domain.PerfilAcesso[0]));
        }
        if(idsRemover.size() > 0){
        	ResultFilterVo<br.com.cdweb.persistence.domain.PerfilAcesso> resultFilterVo = JpaAllEntities.doFilter(br.com.cdweb.persistence.domain.PerfilAcesso.class, 
        			new FieldValuesVo("perfil.idPerfil", idPerfil), 
        			new FieldValuesVo("modeloDispositivo.idModeloDispositivo",idsRemover.toArray(new Long[0])));
        	
        	if(resultFilterVo != null && resultFilterVo.getResultQuery() != null && resultFilterVo.getResultQuery().size() >0 ){
        		JpaAllEntities.delete(JpaAllEntities.merge(resultFilterVo.getResultQuery()).toArray(new br.com.cdweb.persistence.domain.PerfilAcesso[0]));
        	}
        	
        }
    }
}