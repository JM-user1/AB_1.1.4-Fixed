package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
/*Класс UserHibernateDaoImpl в рамках этой задачи не затрагивается (остаётся пустой)*/
public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("create table if not exists user " +
                    "(id bigint not null primary key auto_increment, " +
                    "name varchar(25)," +
                    "lastName varchar(25), " +
                    "age int not null)").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица успешно создана.");
        session.close();
    }

    @Override
    public void dropUsersTable() {//Удалить таблицу Юзер
        Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("drop table if exists user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица успешно удалена.");
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("Успешно добавлен пользователь с именем : " + name);
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User where id = " + id).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Успешно удален пользователь с id : " + id);
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List res = null;
        Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            res = session.createQuery("from User").list();
            session.getTransaction().commit();
        session.close();
        return res;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("truncate table user").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица успешно очищена.");
        session.close();
    }
}
