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

 
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Usuario> buscarTodos() {
//    	List<Usuario> users = JpaAllEntities.listAll(null, null, Usuario.class, OrderType.ASC, UtlEntity.getIdFieldName(Usuario.class)); 
//        return users;
//    }
//
//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Usuario buscarId(@PathParam("id") long id) {
//        return JpaAllEntities.findById(id,Usuario.class);
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Usuario criar(Usuario user) {
//        JpaAllEntities.insert(user);
//        return user;
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Usuario atualizar(Usuario user) {
//        JpaAllEntities.update(user);
//        return user;
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public void remover(@PathParam("id") long id) {
//    	Usuario user = JpaAllEntities.findById(id,Usuario.class);
//    	if(user != null){
//    		JpaAllEntities.delete(user);
//    	}    	
//    }
}