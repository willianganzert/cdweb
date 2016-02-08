package br.com.cdweb.conexao;

public class BroadcastMessage  extends MensagemConexao{
	private String ip;
	
	public BroadcastMessage(String idRemetente, String ip) {
		this(idRemetente);
		this.ip = ip;
	}
	public BroadcastMessage(String idRemetente) {
		super(idRemetente, MensagemConexao.TipoMensagem.BROADCAST);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
