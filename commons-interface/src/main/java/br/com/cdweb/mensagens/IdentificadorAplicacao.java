package br.com.cdweb.mensagens;

public enum IdentificadorAplicacao {
	INSTANCE;
	boolean server = false;
	
	private IdentificadorAplicacao() {
		try {
			Class.forName("javax.servlet.Servlet");
			setServer(true);
		} catch (Exception e) {
			setServer(false);
		}
	}
	 private void setServer(boolean server) {
		this.server = server;
		System.out.println("SERVE ["+server+"]");
	}
	public boolean isServer() {
		return server;
	}
}
