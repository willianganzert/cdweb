package br.com.cdweb.server.startup;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.cdweb.server.filters.Secured;


@Singleton
@Startup
public class ControlAccessBean {
	private Map<Secured.NivelPermicao,List<String>> tokens;
	@PostConstruct
	private void startup() {
		this.tokens = new HashMap<>();
	}
	
	@PreDestroy
	private void shutdown() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println(String.format("[%s] deregistering jdbc driver: %s",Level.INFO, driver));
            } catch (SQLException e) {
            	System.out.println(String.format("[%s] Error deregistering driver % %s",Level.SEVERE, driver));
            	e.printStackTrace();
            }

        }
	}
	
	public void storageToken(Secured.NivelPermicao nivelPermicao, String token){
		if(tokens == null){
			this.tokens = new HashMap<>();
			this.tokens.put(nivelPermicao, new ArrayList<>());
		}
		this.tokens.get(nivelPermicao).add(token);
	}
	
	public boolean tokenIsValid(Secured.NivelPermicao nivelPermicao, String token) {
		if(this.tokens.get(nivelPermicao).contains(token)){
			return true;
		}
		return false;
	}
}
