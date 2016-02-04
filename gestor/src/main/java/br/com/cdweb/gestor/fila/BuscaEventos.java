package br.com.cdweb.gestor.fila;

import java.util.ArrayList;
import java.util.List;

import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.processos.RecebeEvento;

public class BuscaEventos implements Runnable{
	private RecebeEvento<FilaEventoExecutar> encaminhar;
	public BuscaEventos(RecebeEvento<FilaEventoExecutar> encaminhar) {
		this.encaminhar = encaminhar;
	}
	@Override
	public void run() {
				
	}
	
	private List<FilaEventoExecutar> buscaDadosRestfull(){
		List<FilaEventoExecutar> eventoExecutars =  new ArrayList<>();
		//ADD
		
		return eventoExecutars;
	}
}
