package br.com.cdweb;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import br.com.cdweb.dispositivos.configuracoes.Configuracoes;
import br.com.cdweb.dispositivos.fila.FilaExecucao;
import br.com.cdweb.dispositivos.processos.ControladorComunicacaoProxy;
import br.com.cdweb.dispositivos.processos.conexao.AtualizarStatusConexao;
import br.com.cdweb.dispositivos.processos.conexao.WebConection;
import br.com.cdweb.dispositivos.processos.conexao.WifiSocket;
import br.com.cdweb.gestor.fila.BuscaEventos;
import br.com.cdweb.gestor.fila.FilaEvento;
import br.com.cdweb.gestor.fila.GestorFila;

public enum Maestro {
	INSTANCE;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	
	ScheduledFuture evt;
	ScheduledFuture exe;
	ScheduledFuture bevt;
	ScheduledFuture bevte;
	ScheduledFuture wifiSocket;
	ScheduledFuture webConnection;
	ScheduledFuture atuStCon;
	GestorFila gestorFilaEventos;
	GestorFila gestorFilaExecucao;
	GestorFila gestorBuscaEventos;
	
	private Maestro() {
	}
	
	public void iniciarGestorEventos(){
		System.out.println("Iniciando gestor eventos");	
		evt = scheduler.scheduleWithFixedDelay(new GestorFila(FilaEvento.getInstance(FilaExecucao.getInstance(null))),0, 3, TimeUnit.SECONDS);
	}
	public void iniciarGestorExecucao(){
		System.out.println("Iniciando gestor execução");
		exe = scheduler.scheduleWithFixedDelay(new GestorFila(FilaExecucao.getInstance(ControladorComunicacaoProxy.getInstance())),0, 3, TimeUnit.SECONDS);
	}
	
	public void iniciarGestorBuscaEventos(){
		System.out.println("Iniciando gestor busca eventos");
		bevt = scheduler.scheduleWithFixedDelay(new BuscaEventos(FilaEvento.getInstance(FilaExecucao.getInstance(null)),Configuracoes.INSTANCE.getUrlServicosServidorInterno()),0, 3, TimeUnit.SECONDS);
	}
	public void iniciarGestorBuscaEventosExterno(){
		System.out.println("Iniciando gestor busca eventos externo");
		bevte = scheduler.scheduleWithFixedDelay(new BuscaEventos(FilaEvento.getInstance(FilaExecucao.getInstance(null)), Configuracoes.INSTANCE.getUrlServicosServidorExterno()),0, 3, TimeUnit.SECONDS);
	}
	
	public void pararGestorEventos(){
		System.out.println("Parando gestor eventos");
		evt.cancel(true);
	}
	public void pararGestorExecucao(){
		System.out.println("Parando gestor execução");
		exe.cancel(true);
	}
	
	public void pararGestorBuscaEventos(){
		System.out.println("Parando gestor busca eventos");
		bevt.cancel(true);
	}
	
	public void pararGestorBuscaEventosExterno(){
		System.out.println("Parando gestor busca eventos externo");
		bevte.cancel(true);
	}

	public void iniciarControleStatusConexao() {
		System.out.println("Iniciando Status de Conexao");
		
		wifiSocket = scheduler.schedule(new WifiSocket(), 0, TimeUnit.SECONDS);
		webConnection = scheduler.schedule(new WebConection(), 0, TimeUnit.SECONDS);
		
		atuStCon = scheduler.scheduleWithFixedDelay(new AtualizarStatusConexao(),0, 2, TimeUnit.SECONDS);
	}
}
