package br.com.cdweb.persistence;

import org.junit.Assert;
import org.junit.Test;

import br.com.cdweb.persistence.domain.Usuario;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import junit.framework.TestCase;

public class JpaTest extends TestCase{
	public static void main(String[] args) {
		new JpaTest().testCRUD();
	}
	@Test
	public void testCRUD(){
		Usuario usuario = new Usuario();
		usuario.setLogin("mairalima");
		usuario.setSenha("mairalima");
		JpaAllEntities.insertOrUpdate(usuario);
		long idUsuario = usuario.getId();		
		JpaAllEntities.delete(usuario);
		Usuario usuarioRemovido = JpaAllEntities.findById(idUsuario, Usuario.class);
		
		Assert.assertNull("Usuario test nao pode ser removido", usuarioRemovido);
	}	
}

