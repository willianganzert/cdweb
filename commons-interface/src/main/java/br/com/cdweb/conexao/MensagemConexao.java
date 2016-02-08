package br.com.cdweb.conexao;

public class MensagemConexao {
	public enum TipoMensagem{
		BROADCAST,
		TESTA_CONEXAO_SERVER_WEB
		
	}
	private String idRemetente;
	private TipoMensagem tipoMensagem;
	
	public MensagemConexao(String idRemetente, TipoMensagem tipoMensagem) {
		this.idRemetente = idRemetente;
		this.tipoMensagem = tipoMensagem;	
	}
	


	public String getIdRemetente() {
		return idRemetente;
	}



	public void setIdRemetente(String idRemetente) {
		this.idRemetente = idRemetente;
	}



	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}
	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}	
}
