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

import br.com.cdweb.persistence.domain.Perfil;
import br.com.cdweb.persistence.domain.Usuario;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.server.filters.Secured;

@Path("usuariosperfis")
public class UsuarioPerfil {

	@Context
	private HttpServletRequest httpRequest;
	
	

	@Secured
    @GET
    @Path("/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Long[] buscarIdsPerfisByUsuario(@PathParam("id") long id) {
		List<Long> idsPerfis = new ArrayList<>();
		Usuario usuario = JpaAllEntities.findById(id, Usuario.class);
		ResultFilterVo<br.com.cdweb.persistence.domain.UsuarioPerfil> resultFilterVo = JpaAllEntities.doFilter(br.com.cdweb.persistence.domain.UsuarioPerfil.class, new FieldValuesVo("usuario", usuario));
		if(resultFilterVo != null && !resultFilterVo.getResultQuery().isEmpty()){
			for (br.com.cdweb.persistence.domain.UsuarioPerfil perfil: resultFilterVo.getResultQuery()) {
				idsPerfis.add(perfil.getPerfil().getIdPerfil());
			}
			
		}
		
        return idsPerfis.toArray(new Long[0]);
    }
	
	@Secured
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void atualizar(@PathParam("id") long idUsuario, Long[] ids) {
        Long[] idsAtuais = buscarIdsPerfisByUsuario(idUsuario);
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
        	List<br.com.cdweb.persistence.domain.UsuarioPerfil> usuarioPerfils = new ArrayList<>();
        	for (Long long1 : idsAdicionar) {
        		br.com.cdweb.persistence.domain.UsuarioPerfil usuarioPerfil =  new br.com.cdweb.persistence.domain.UsuarioPerfil();
        		usuarioPerfil.setDataAtribuicao(new Date());
        		usuarioPerfil.setUsuario(new Usuario(idUsuario));
        		usuarioPerfil.setPerfil(new Perfil(long1));
        		
        		usuarioPerfils.add(usuarioPerfil);
			}
        	JpaAllEntities.insert(usuarioPerfils.toArray(new br.com.cdweb.persistence.domain.UsuarioPerfil[0]));
        }
        if(idsRemover.size() > 0){
        	ResultFilterVo<br.com.cdweb.persistence.domain.UsuarioPerfil> resultFilterVo = JpaAllEntities.doFilter(br.com.cdweb.persistence.domain.UsuarioPerfil.class, 
        			new FieldValuesVo("usuario.idUsuario", idUsuario), 
        			new FieldValuesVo("perfil.idPerfil",idsRemover.toArray(new Long[0])));
        	
        	if(resultFilterVo != null && resultFilterVo.getResultQuery() != null && resultFilterVo.getResultQuery().size() >0 ){
        		JpaAllEntities.delete(JpaAllEntities.merge(resultFilterVo.getResultQuery()).toArray(new br.com.cdweb.persistence.domain.UsuarioPerfil[0]));
        	}
        	
        }
    }
}