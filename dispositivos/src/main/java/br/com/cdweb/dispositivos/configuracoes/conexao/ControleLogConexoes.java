/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cdweb.dispositivos.configuracoes.conexao;

/**
 *
 * @author Willian
 */
public enum ControleLogConexoes {
	INSTANCE;
	private long horaUltimaConexaoServerWIFI;
	private long horaUltimaConexaoServerWEB;
	private ControleLogConexoes() {
		this.horaUltimaConexaoServerWIFI = 0;
		this.horaUltimaConexaoServerWEB = 0;
	}
	
	public void setHoraUltimaConexaoServerWEB(long horaUltimaConexaoServerWEB) {
		this.horaUltimaConexaoServerWEB = horaUltimaConexaoServerWEB;
	}
	
	public void setHoraUltimaConexaoServerWIFI(long horaUltimaConexaoServerWIFI) {
		this.horaUltimaConexaoServerWIFI = horaUltimaConexaoServerWIFI;
	}

	public long getHoraUltimaConexaoServerWIFI() {
		return horaUltimaConexaoServerWIFI;
	}

	public long getHoraUltimaConexaoServerWEB() {
		return horaUltimaConexaoServerWEB;
	}
	
	
}
