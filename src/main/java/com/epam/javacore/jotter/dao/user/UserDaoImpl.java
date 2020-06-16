package com.epam.javacore.jotter.dao.user;

import com.epam.javacore.jotter.domain.user.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User selectUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User selectUser(String login) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
    }

    public List<User> selectUserList(String login, String searchLogin){
        StringBuilder query=new StringBuilder("SELECT u FROM User u WHERE u.login <> :login");
        if(searchLogin!=null){
            query.append(" AND u.login=:"+searchLogin);
        }

        return entityManager.createQuery("SELECT u FROM User u WHERE u.login <> :login").setParameter("login", login).getResultList();
    }

    @Override
    public void deleteUser(long id) {
            User user=entityManager.find(User.class, id);
            user.getNoteList();
            entityManager.remove(user);
    }
}
