package br.com.cdweb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import br.com.cdweb.dispositivos.fila.FilaExecucao;
import br.com.cdweb.gestor.fila.Fila;
import br.com.cdweb.gestor.fila.FilaEvento;
import br.com.cdweb.gestor.fila.GestorFila;

public enum Maestro {
	INSTANCE;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
	
	ScheduledFuture evt;
	ScheduledFuture exe;
	GestorFila gestorFilaEventos;
	GestorFila gestorFilaExecucao;
	
	private Maestro() {
	}
	
	public void iniciarGestorEventos(){
		System.out.println("Iniciando gestor eventos");	
		evt = scheduler.scheduleWithFixedDelay(new GestorFila(FilaEvento.getInstance()),0, 3, TimeUnit.SECONDS);
	}
	public void iniciarGestorExecucao(){
		System.out.println("Iniciando gestor execução");
		exe = scheduler.scheduleWithFixedDelay(new GestorFila(FilaExecucao.getInstance()),0, 3, TimeUnit.SECONDS);
	}
	
	public void pararGestorEventos(){
		System.out.println("Parando gestor eventos");
		evt.cancel(true);
	}
	public void pararGestorExecucao(){
		System.out.println("Parando gestor execução");
		exe.cancel(true);
	}
}
