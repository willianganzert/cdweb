package br.com.cdweb.dispositivos.processos.conexao;

import br.com.cdweb.dispositivos.configuracoes.Configuracoes;
import br.com.cdweb.dispositivos.configuracoes.conexao.ControleLogConexoes;
import br.com.cdweb.dispositivos.configuracoes.conexao.StatusConexao;

public class AtualizarStatusConexao implements Runnable {
	private long tempoConsiderarWifiOffmilis = 15000;
	private long tempoConsiderarWebOffmilis = 15000;
	@Override
	public void run() {
		apurarStatusConexaoWifi();
		apurarStatusConexaoWeb();
	}


	private void apurarStatusConexaoWifi() {
		long ultCon = ControleLogConexoes.INSTANCE.getHoraUltimaConexaoServerWIFI();
		if(Configuracoes.INSTANCE.getConexaoServerWIFI().equals(StatusConexao.CONECTADO)){
			if(System.currentTimeMillis() - ultCon > tempoConsiderarWifiOffmilis){
				Configuracoes.INSTANCE.setConexaoServerWIFI(StatusConexao.DESCONECTADO);
			}			
		}
		else if(Configuracoes.INSTANCE.getConexaoServerWIFI().equals(StatusConexao.DESCONECTADO)){
			if(System.currentTimeMillis() - ultCon < tempoConsiderarWifiOffmilis){
				Configuracoes.INSTANCE.setConexaoServerWIFI(StatusConexao.CONECTADO);
			}			
		}
	}
	
	private void apurarStatusConexaoWeb() {
		long ultCon = ControleLogConexoes.INSTANCE.getHoraUltimaConexaoServerWEB();
		if(Configuracoes.INSTANCE.getConexaoServerWEB().equals(StatusConexao.CONECTADO)){
			if(System.currentTimeMillis() - ultCon > tempoConsiderarWebOffmilis){
				Configuracoes.INSTANCE.setConexaoServerWEB(StatusConexao.DESCONECTADO);
			}			
		}
		else if(Configuracoes.INSTANCE.getConexaoServerWEB().equals(StatusConexao.DESCONECTADO)){
			if(System.currentTimeMillis() - ultCon < tempoConsiderarWebOffmilis){
				Configuracoes.INSTANCE.setConexaoServerWEB(StatusConexao.CONECTADO);
			}			
		}
	}

}
