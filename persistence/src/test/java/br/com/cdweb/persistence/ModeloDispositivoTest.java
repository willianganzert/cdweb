package br.com.cdweb.persistence;

import java.util.List;

import org.junit.Assert;

import br.com.cdweb.persistence.domain.ModeloDispositivo;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import junit.framework.TestCase;

public class ModeloDispositivoTest{
	public static void main(String[] args) {
		new ModeloDispositivoTest().buscaTodosModelosDispositivo();
	}
	public void buscaTodosModelosDispositivo(){
		List<ModeloDispositivo> modeloDispositivos = JpaAllEntities.listAll(ModeloDispositivo.class);
		for (ModeloDispositivo modeloDispositivo : modeloDispositivos) {
			System.out.println(modeloDispositivo);
		}
		Assert.assertNull("bbbbbbbbbbb",null);
	}
}
