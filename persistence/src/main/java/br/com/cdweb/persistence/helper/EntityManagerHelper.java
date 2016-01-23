package br.com.cdweb.persistence.helper;


import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManager;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * 
 * @author willian
 *
 */
public class EntityManagerHelper {	
	private static final EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal;
    private static Properties propertiesMap;


    static {
//    	ClassLoader classLoader = EntityManagerHelper.class.getClassLoader();
//    	propertiesMap = new Properties();
////		propertiesMap.put(PersistenceUnitProperties.APP_LOCATION, classLoader.getResource(".").getFile());
//		propertiesMap.put(PersistenceUnitProperties.CREATE_JDBC_DDL_FILE, "dasssdosIniciais.sql");
		    	
    	
    	String persistenceUnitName = "cdwebPersistenceUnitLocal";
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        threadLocal = new ThreadLocal<EntityManager>();
    }

    public static EntityManager getEntityManager() {
        EntityManager manager = threadLocal.get();
        if (manager == null || !manager.isOpen()) {
            manager = emf.createEntityManager();
            threadLocal.set(manager);
        }
        return manager;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        threadLocal.set(null);
        if (em != null) {
            em.close();
        }
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void commit() {
        getEntityManager().getTransaction().commit();
    }
}
