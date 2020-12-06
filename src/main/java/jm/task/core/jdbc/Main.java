package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Petya", "Pupkin", (byte) 34);
        usi.saveUser("Vasya", "Ivanov", (byte) 12);
        usi.saveUser("Olya", "Pupkina", (byte) 47);
        usi.saveUser("Ira", "Allegro", (byte) 26);

//        usi.removeUserById(2);
        List<User> list = usi.getAllUsers();
        list.stream().forEach(System.out::println);
//        for (Iterator i=list.iterator(); i.hasNext();){
//            User u=(User)i.next();
//            System.out.println(u.toString());
//        }

        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
