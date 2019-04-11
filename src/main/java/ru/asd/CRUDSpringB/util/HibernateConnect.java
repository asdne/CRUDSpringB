package ru.asd.CRUDSpringB.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.asd.CRUDSpringB.entity.Person;


public class HibernateConnect {
    private static HibernateConnect instance;
    private final SessionFactory sessionFactory;

    static {
        instance = new HibernateConnect();
    }

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private HibernateConnect() {
        Configuration configuration = getMySQLConfigurationHib();
        sessionFactory = createSessionFactory(configuration);


    }

    private Configuration getMySQLConfigurationHib() {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/javalearning");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static HibernateConnect getInstance() {
        return instance;
    }


    public SessionFactory getConnection() {
        return this.sessionFactory;
    }
}
