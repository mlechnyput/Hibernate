package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.toConnect();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS base151.usertable(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (40) NOT NULL ," +
                    "lastName VARCHAR (40) NOT NULL ," +
                    "age INT NOT NULL, PRIMARY KEY (id))" +
                    "DEFAULT CHARACTER SET = utf8;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.toConnect();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS base151.usertable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.toConnect();
        String str = "INSERT INTO base151.usertable (name, lastName, age) VALUES (?,?,?);";

        try (PreparedStatement pst = connection.prepareStatement(str)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();
            System.out.println("пользователь с именем " + name + " добавлен в БД");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.toConnect();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM base151.usertable WHERE id=" + id + ";");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.toConnect();
        List<User> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM base151.usertable;");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = Util.toConnect();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM base151.usertable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
