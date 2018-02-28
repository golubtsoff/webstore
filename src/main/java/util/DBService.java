package util;

import entity.Item;
import entity.Person;
import entity.Purchase;
import entity.Role;
import exception.ServiceException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class DBService {
    private static final String hibernate_show_sql = "true";
//    So the list of possible options are,
//
//    validate: validate the schema, makes no changes to the database.
//    update: update the schema.
//    create: creates the schema, destroying previous data.
//    create-drop: drop the schema when the SessionFactory is closed explicitly, typically when the application is stopped.
    private static final String hibernate_hbm2ddl_auto = "create";

    private static final String filename = "database.properties";

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = getConfiguration();
            sessionFactory = createSessionFactory(configuration);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private DBService() {
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        addAnnotatedClassToConfiguration(configuration);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/webstore?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        addAnnotatedClassToConfiguration(configuration);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

        return configuration;
    }

    private static Configuration getConfiguration() throws ServiceException {
        Configuration configuration = new Configuration();
        addAnnotatedClassToConfiguration(configuration);
        String path = DBService.class.getClassLoader().getResource(".").getPath() + filename;

        try (InputStream is = new FileInputStream(path)) {
            Properties props = new Properties();
            props.load(is);

            configuration.setProperty("hibernate.dialect", props.getProperty("hibernate.dialect"));
            configuration.setProperty("hibernate.connection.driver_class", props.getProperty("hibernate.connection.driver_class"));
            configuration.setProperty("hibernate.connection.url", props.getProperty("hibernate.connection.url"));
            configuration.setProperty("hibernate.connection.username", props.getProperty("hibernate.connection.username"));
            configuration.setProperty("hibernate.connection.password", props.getProperty("hibernate.connection.password"));
            configuration.setProperty("hibernate.show_sql", props.getProperty("hibernate.show_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", props.getProperty("hibernate.hbm2ddl.auto"));

        } catch (IOException e) {
            throw new ServiceException("Invalid config file " + path);
        }
        return configuration;
    }

    private static void addAnnotatedClassToConfiguration(Configuration configuration) {
        configuration.addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Purchase.class)
                .addAnnotatedClass(Role.class);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void close(){
        sessionFactory.close();
    }
}
