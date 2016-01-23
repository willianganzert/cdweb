package dispositivos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import br.com.cdweb.gestor.fila.FilaEvento;
import br.com.cdweb.gestor.fila.GestorFila;

public class GestorTest {
	ExecutorService exe = Executors.newFixedThreadPool(2);
	@Test
	public void test() {
		GestorFila gestorFila = new GestorFila(FilaEvento.getInstance());
		exe.submit(gestorFila);
		
		
	}

}