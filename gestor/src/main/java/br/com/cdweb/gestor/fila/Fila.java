package br.com.cdweb.gestor.fila;

import java.util.Collections;
import java.util.List;

import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.processos.RecebeEvento;

public abstract class Fila<T> {
	private String tipoFila;
	private List<T> listaFilaMemoria = null;
	private T item = null;
	protected boolean configurado = false;
	
	public Fila(String tipoFila) {
		this.tipoFila = tipoFila;
		carregaListaFilaMemoria();
		System.out.println(String.format("Iniciado fila[%s] - Lista size[%d]",this.tipoFila,listaFilaMemoria.size()));
	}

	
	protected abstract List<T> carregarLista();	
	protected abstract boolean encaminhar(T item);	
	protected abstract void gravarErro(T item2);
	protected abstract void gravarSucesso(T item2);
	protected abstract void adicionarImp(T item2);
	protected abstract void removerImp(T item2);
	
	public abstract void configurarEncaminhar(RecebeEvento<FilaEventoExecutar> recebeEvento);
	
	public boolean isConfigurado() {
		return configurado;
	}
	

	private void carregaListaFilaMemoria(){
		List<T> listaCarregada = carregarLista();
		if(listaFilaMemoria == null){
			listaFilaMemoria = Collections.synchronizedList(listaCarregada);
		}
		else{
			listaFilaMemoria.addAll(listaCarregada);
		}
	}
	protected T proximo(){
		if(listaFilaMemoria.size() > 0){
			item = listaFilaMemoria.get(0);		
		}
		else {
			carregaListaFilaMemoria();
			if(listaFilaMemoria.size() > 0){
				item = proximo();
			}
		}		
		return item;
	}
	
	public boolean processar() {
		boolean sucesso = true;
		while (proximo()!= null) {
			if(encaminhar(item)){
				gravarSucesso(item);				
			}
			else{
				gravarErro(item);
			}
			remover(item);			
		}
		return sucesso;
	}

	public void remover(T item2) {
		listaFilaMemoria.remove(item2);
		removerImp(item2);
	}
	
	public void adicionar(T item2) {
		listaFilaMemoria.add(item2);
		removerImp(item2);
	}
	
}
