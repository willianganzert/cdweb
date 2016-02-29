package br.com.cdweb.server.services;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cdweb.persistence.domain.Token;
import br.com.cdweb.persistence.domain.Usuario;
import br.com.cdweb.persistence.domain.UsuarioPerfil;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.server.startup.ControlAccessBean;

@Path("/authentication")
public class AuthenticationService {
	
	private ControlAccessBean controlAccessBean = new ControlAccessBean();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("u") String username, 
                                     @FormParam("p") String password) {
    	System.out.println(String.format("INICIA AUTENTICACAO\nPARAMS user:%s pass:%s",username,password));
    	
        try {
        	username = new String(Base64.getDecoder().decode(username));
        	password = new String(Base64.getDecoder().decode(password));
        	System.out.println(String.format("INICIA AUTENTICACAO\nPARAMS user:%s pass:%s",username,password));
        	

            Token token = authAndGenerateToken(username, password);
            controlAccessBean.storageToken(token);
            ResultFilterVo<UsuarioPerfil> filterUsuariPerfil = JpaAllEntities.doFilter(UsuarioPerfil.class, new FieldValuesVo("usuario", token.getUsuario()));
            StringBuilder builder = new StringBuilder();
            List<UsuarioPerfil> usuarioPerfils = filterUsuariPerfil.getResultQuery();
            if(usuarioPerfils != null && usuarioPerfils.size() > 0){
            	for (UsuarioPerfil usuarioPerfil : usuarioPerfils) {
    				if(builder.length() > 0){
    					builder.append(',');
    				}
    				builder.append('"');
    				builder.append(usuarioPerfil.getPerfil().getNome().toLowerCase());
    				builder.append('"');
    			}
            }
            else{
            	builder.append("\"administrador\"");
            }
            
            return Response.ok("[\"" + token.getGeneratedToken() + "\",["+builder.toString()+"]]").build();

        } catch (Exception e) {
        	System.out.println("ERRO AUTENTICAÇÃO");
        	e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }
	private Token authAndGenerateToken(String username, String password) throws Exception {
		// Authenticate the user using the credentials provided
		Token token = authenticate(username, password);
		if(token == null){
			throw new Exception("Usuário/Senha Inválido.");
		}		
		System.out.println("AUTENTICACAO TOKEN - " + token.getGeneratedToken());
		// Return the token on the responseencontra onde es
		return token;
	}

    private Token authenticate(String username, String password){
    	ResultFilterVo<Usuario> filterVo = JpaAllEntities.doFilter(Usuario.class, new FieldValuesVo("login", username), new FieldValuesVo("senha", password));
        if(filterVo.getResultQuery().size() == 1){
        	List<Usuario> usuarios = filterVo.getResultQuery();
        	Token token = new Token();
        	token.setGeneratedToken(issueToken(username));
        	token.setHoraCriacao(new Date());
        	token.setUsuario(usuarios.get(0));
        	return token;
        }
        else{
        	return null;
        }
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	return UUID.randomUUID().toString();
    }
}
