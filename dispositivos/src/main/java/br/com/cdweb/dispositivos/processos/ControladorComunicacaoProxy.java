package br.com.cdweb.dispositivos.processos;

import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.processos.RecebeEvento;

public class ControladorComunicacaoProxy implements RecebeEvento<FilaEventoExecutar>{
	private static ControladorComunicacaoProxy INSTANCE;
	
	private ControladorComunicacaoProxy() {
		
	}
	
	public static ControladorComunicacaoProxy getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ControladorComunicacaoProxy();
		}
		return INSTANCE;
	}
	
	@Override
	public void recebeEvento(FilaEventoExecutar evento) {
		System.out.println("==============================================================================================");
		System.out.println("====================================EXECUTADO EVENTO==========================================");
		System.out.println("==============================================================================================");
		System.out.println(evento.getModeloAcao());
		System.out.println(evento);		
	}

}
