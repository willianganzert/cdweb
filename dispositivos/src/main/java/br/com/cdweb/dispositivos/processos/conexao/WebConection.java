package br.com.cdweb.dispositivos.processos.conexao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

import br.com.cdweb.conexao.MensagemConexao;
import br.com.cdweb.dispositivos.configuracoes.Configuracoes;
import br.com.cdweb.dispositivos.configuracoes.conexao.ControleLogConexoes;

public class WebConection implements Runnable{
	private Gson gson;
	public WebConection() {
		gson = new Gson();
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		try {
			URL oracle = new URL("http://nerdti.com/server/rest/testeConexao");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));

	        String inputLine;
	        String response = "";
	        while ((inputLine = in.readLine()) != null){
//	            System.out.println(inputLine);
	            response += inputLine;
	        }
			MensagemConexao mensagemConexao = gson.fromJson(response, MensagemConexao.class);
			if(mensagemConexao.getTipoMensagem().equals(MensagemConexao.TipoMensagem.TESTA_CONEXAO_SERVER_WEB)){
				ControleLogConexoes.INSTANCE.setHoraUltimaConexaoServerWEB(System.currentTimeMillis());
			}
				
	        in.close();
		} catch (IOException e) {
			Configuracoes.INSTANCE.informarServerWEBOffline();
//			e.printStackTrace();
		}
		}
	}
}
