package br.com.cdweb.server.startup;

import java.util.ArrayList;
import java.util.List;

import br.com.cdweb.persistence.domain.Token;
import br.com.cdweb.persistence.jpa.JpaAllEntities;


public class ControlAccessBean {
	

	public ControlAccessBean() {		
	}
	
	public void storageToken(Token token){
		JpaAllEntities.insert(token);
	}
	
	public boolean tokenIsValid(String token) {
		List<String>tokens = new ArrayList<String>();
		List<Token> filterVo = JpaAllEntities.listAll(Token.class);
		for (Token tokenFor : filterVo) {
			tokens.add(tokenFor.getGeneratedToken());
		}
		if(tokens.contains(token)){
			return true;
		}
		return false;
	}
}
