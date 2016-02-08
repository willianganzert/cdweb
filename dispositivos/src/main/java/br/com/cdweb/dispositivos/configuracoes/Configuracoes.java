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
    private StatusConexao conexaoServerWIFI;
    private StatusConexao conexaoServerWEB;

    private Configuracoes(String name) {
        this.name = name;
        conexaoServerWIFI = StatusConexao.DESCONECTADO;
        conexaoServerWEB = StatusConexao.DESCONECTADO;
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

    
    
}
