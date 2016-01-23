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
	T,
	/**
	 * Em Processamento [Servidor Externo]
	 */
	D,
	/**
	 * Pendente de Avaliação [Servidor Interno]
	 */
	A,
	/**
	 * Pendente de processamento [Servidor Interno]
	 */
	P,
	/**
	 * Em processamento [Servidor Interno]
	 */
	R,
	/**
	 * Erro [Servidor Interno]
	 */
	E,
	/**
	 * Processada [Servidor Interno]
	 */
	K;
	
	public StatusMensagem getStatusPendenteServer(int tipoServer){
		if(tipoServer == 0){
			return P;
		}
		else {
			return T; 
		}
	}
}
