/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cdweb.dispositivos.configuracoes;

import br.com.cdweb.dispositivos.configuracoes.conexao.StatusConexao;

/**
 *
 * @author Willian
 */
public enum Configuracoes {
    INSTANCE("DISPO_01");
    private String name;
    private final static String PREFIX_SERVICOS = "http://";
    private final static String SUFIX_SERVICOS = "server/rest";
    private StatusConexao conexaoServerWIFI;
    private StatusConexao conexaoServerWEB;
    private String urlServicosServidorExterno;
    private String urlServicosServidorInterno;
    

    private Configuracoes(String name) {
        this.name = name;
        conexaoServerWIFI = StatusConexao.DESCONECTADO;
        conexaoServerWEB = StatusConexao.DESCONECTADO;
        this.urlServicosServidorExterno = PREFIX_SERVICOS + "nerdti.com" + "/" + SUFIX_SERVICOS;
        this.urlServicosServidorInterno = "";
    }

    public String getUrlServicosServidorInterno() {
		return urlServicosServidorInterno;
	}

	public void setUrlServicosServidorInterno(String host) {
		this.urlServicosServidorInterno = PREFIX_SERVICOS + host + ":8080/" + SUFIX_SERVICOS;
		System.out.println(this.urlServicosServidorInterno);
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

	public StatusConexao getConexaoServerWIFI() {
		return conexaoServerWIFI;
	}

	public void setConexaoServerWIFI(StatusConexao conexaoServerWIFI) {
		this.conexaoServerWIFI = conexaoServerWIFI;
	}

	public StatusConexao getConexaoServerWEB() {
		return conexaoServerWEB;
	}

	public void setConexaoServerWEB(StatusConexao conexaoServerWEB) {
		this.conexaoServerWEB = conexaoServerWEB;
	}
	
	public void informarServerWEBOffline(){
		this.conexaoServerWEB = StatusConexao.DESCONECTADO;
	}

	public String getUrlServicosServidorExterno() {
		return urlServicosServidorExterno;
	}    
    
}
