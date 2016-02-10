package br.com.cdweb.processos;

import java.util.List;

public interface RecebeEvento<T> {
	public void recebeEvento(T evento);
	public void recebeEvento(List<T> evento);
}
