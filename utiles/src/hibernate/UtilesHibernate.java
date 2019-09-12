package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

public class UtilesHibernate {

	private static final SessionFactory sessionFactory;
	
	static {
		
		try {
			//Lee el fichero de configuracion y crea el sessionFactory
			Configuration configuration = new Configuration().configure();
			
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());

		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void openSession(){
		Session session = sessionFactory.openSession();
		
		if(session!=null){
			ThreadLocalSessionContext.bind(session);
		}
	}
	
	public static void closeSession(){
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if(session != null){
			
			session.close();
		}
		
	}
	
	public static void closeSessionFactory(){
		
		if((sessionFactory != null)&&(sessionFactory.isClosed()==false)){
			
			sessionFactory.close();
		}
		
	}
	
}
