package br.com.cdweb.gestor.fila;

public class GestorFila implements Runnable{
	private Fila fila;
	public GestorFila(Fila fila) {
		this.fila = fila;
	}
	@Override
	public void run() {
		fila.processar();		
	}
	
}
