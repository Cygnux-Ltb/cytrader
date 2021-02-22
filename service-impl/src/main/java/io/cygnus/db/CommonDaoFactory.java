package io.cygnus.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import io.cygnus.service.entity.CygInfo;

public class CommonDaoFactory {

	private static SessionFactory sessionFactory;

	static {
		try {
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void close(Session session) {
		if (session != null) {
			session.close();
		}
	}

	public static void main(String[] args) {
		Session session = CommonDaoFactory.getSession();
		Criteria criteria = session.createCriteria(CygInfo.class);
		System.out.println(criteria.list());
	}

}
