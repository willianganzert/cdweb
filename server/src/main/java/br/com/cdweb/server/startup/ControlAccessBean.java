package br.com.cdweb.server.startup;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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
