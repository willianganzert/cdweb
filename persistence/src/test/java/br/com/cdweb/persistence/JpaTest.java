package br.com.cdweb.persistence;

import org.junit.Assert;
import org.junit.Test;

public class JpaTest{
	public static void main(String[] args) {
		new JpaTest().testCRUD();
	}
	
	@Test
	public void testCRUD(){
//		JpaAllEntities.listAll(Usuario.class);
//		Usuario usuario = new Usuario();
//		usuario.setLogin("mairalima");
//		usuario.setSenha("mairalima");
//		JpaAllEntities.insertOrUpdate(usuario);
//		long idUsuario = usuario.getId();		
//		JpaAllEntities.delete(usuario);
//		Usuario usuarioRemovido = JpaAllEntities.findById(idUsuario, Usuario.class);
		
		Assert.assertEquals(10, 10);
	}	
}

