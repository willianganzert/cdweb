package br.com.cdweb.mensagens;

/**
 *	Stados das mensagens no sistema
 *
 * @author Willian
 *
 */
public enum StatusMensagem {
	/**
	 * Pendente no [Servidor Externo]
	 */
	Q,
	
	/**
	 * Em Processamento [Servidor Externo]
	 */
	W,
	
	/**
	 * Erro [Servidor Externo]
	 */
	E,
	
	/**
	 * Processada [Servidor Externo]
	 */
	R,
	
	
	
	/**
	 * Pendente de processamento [Servidor Interno]
	 */
	S,
	
	/**
	 * Em processamento [Servidor Interno]
	 */
	D,
	
	/**
	 * Erro [Servidor Interno]
	 */
	F,
	
	/**
	 * Processada [Servidor Interno]
	 */
	G;
	
	public static StatusMensagem pendente(){
		if(IdentificadorAplicacao.INSTANCE.isServer()){
			return Q;
		}
		else {
			return S; 
		}
	}
	
	public static StatusMensagem processando(){
		if(IdentificadorAplicacao.INSTANCE.isServer()){
			return W;
		}
		else {
			return D; 
		}
	}
	
	public static StatusMensagem processada(){
		if(IdentificadorAplicacao.INSTANCE.isServer()){
			return R;
		}
		else {
			return F; 
		}
	}
	
	public static StatusMensagem erro(){
		if(IdentificadorAplicacao.INSTANCE.isServer()){
			return E;
		}
		else {
			return G; 
		}
	}
}
