package br.com.cdweb.conexao;

public class WebTesteMessage  extends MensagemConexao{
	private String ip;
	
	public WebTesteMessage(String idRemetente, String ip) {
		this(idRemetente);
		this.ip = ip;
	}
	public WebTesteMessage(String idRemetente) {
		super(idRemetente, MensagemConexao.TipoMensagem.TESTA_CONEXAO_SERVER_WEB);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
