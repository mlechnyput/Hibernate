package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/base151";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mameluko";


    public static Connection toConnect() {
        Connection connection = null;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory toConnectHiber() {

        //конфиги
        Configuration config = new Configuration();
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("hibernate.connection.url", URL);
        prop.setProperty("hibernate.connection.username", USERNAME);
        prop.setProperty("hibernate.connection.password", PASSWORD);
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.show.sql", "true");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");

        config.setProperties(prop);
        config.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder();
        sBuilder.applySettings(config.getProperties());
        SessionFactory sf = config.buildSessionFactory(sBuilder.build());

        if (!sf.isClosed()) System.out.println("соединение установлено");
        return sf;
    }
}
