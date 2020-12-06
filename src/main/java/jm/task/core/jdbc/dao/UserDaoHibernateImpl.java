package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction tr = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sf = Util.toConnectHiber();
        Session session = sf.openSession();
        tr = session.beginTransaction();
        String query = "CREATE TABLE IF NOT EXISTS base151.usertablehiber (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(40) NOT NULL," +
                "lastName VARCHAR(40) NOT NULL," +
                "age INT(3) NOT NULL," +
                "PRIMARY KEY (id))" +
                "DEFAULT CHARACTER SET = utf8;";
        session.createSQLQuery(query).executeUpdate();
        tr.commit();
        session.close();
        sf.close();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sf = Util.toConnectHiber();
        Session session = sf.openSession();
        tr = session.beginTransaction();
        String query = "DROP TABLE IF EXISTS base151.usertablehiber;";
        session.createSQLQuery(query).executeUpdate();
        tr.commit();
        session.close();
        sf.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sf = Util.toConnectHiber();
        Session session = sf.openSession();
        tr = session.beginTransaction();
        Serializable x = session.save(new User(name, lastName, age));
        tr.commit();
        System.out.println("пользователь " + name + " добавлен в базу под номером " + x);
        session.close();
        sf.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sf = Util.toConnectHiber();
        Session session = sf.openSession();
        tr = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        if (user != null) session.delete(user);
        tr.commit();
        session.close();
        sf.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sf = Util.toConnectHiber();
        Session session = sf.openSession();
        tr = session.beginTransaction();
        Criteria entity = session.createCriteria(User.class);
        List<User> list = entity.list();
        tr.commit();
        session.close();
        sf.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sf = Util.toConnectHiber();
        Session session = sf.openSession();
        tr = session.beginTransaction();
        String query = "DELETE FROM User";
        session.createQuery(query).executeUpdate();
        tr.commit();
        session.close();
        sf.close();
    }
}
