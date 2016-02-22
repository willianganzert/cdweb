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


@Singleton
@Startup
public class ControlAccessBean {
	private List<String> tokens;
	@PostConstruct
	private void startup() {
		System.out.println("=======================");
		System.out.println("=====INICIANDO CDWEB======");
		System.out.println("=======================");
		
		this.tokens = new ArrayList<String>();
	}
	
	@PreDestroy
	private void shutdown() { 
		System.out.println("=======================");
		System.out.println("=====FINALIZANDO CDWEB======");
		System.out.println("=======================");	
		
		// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println(String.format("[%s] deregistering jdbc driver: %s",Level.INFO, driver));
//                LOG.log(Level.INFO, String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
            	System.out.println(String.format("[%s] Error deregistering driver % %s",Level.SEVERE, driver));
            	e.printStackTrace();
//                LOG.log(Level.SEVERE, String.format("Error deregistering driver %s", driver), e);
            }

        }
	}
	
	public void storageToken(String token){
		if(tokens == null){
			this.tokens = new ArrayList<String>();
		}
		this.tokens.add(token);
	}
	
	public boolean tokenIsValid(String token) {
		if(this.tokens.contains(token)){
			return true;
		}
		return false;
	}
}
