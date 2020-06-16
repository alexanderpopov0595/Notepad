package com.epam.javacore.jotter.dao.subscribe;

import com.epam.javacore.jotter.domain.user.Subscriber;
import com.epam.javacore.jotter.domain.user.User;
import com.epam.javacore.jotter.enums.Status;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SubscribeDaoImpl implements SubscribeDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addSubscriber(Subscriber subscriber) {
        entityManager.persist(subscriber);
    }

    @Override
    public void acceptSubscriber(long id) {
        entityManager.createQuery("UPDATE  Subscriber s SET s.status=:status WHERE s.id=:id")
                .setParameter("status", Status.SUBSCRIBED)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteSubscriber(long id) {
        entityManager.createQuery("DELETE FROM Subscriber s WHERE s.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Subscriber> selectSubscriberList(String login) {
        return entityManager.createQuery("SELECT s FROM Subscriber s JOIN User u ON s.user.id=u.id WHERE u.login=:login")
                .setParameter("login", login)
                .getResultList();
    }

    @Override
    public List<Subscriber> selectSubscriptionList(String login) {
        User user=(User) entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
        return entityManager.createQuery("SELECT s FROM Subscriber s WHERE s.subscriber.id=:id").setParameter("id", user.getId()).getResultList();
      //  return entityManager.createQuery("SELECT u FROM User u JOIN Subscriber s ON u.id=s.subscriber.id WHERE u.login=:login")
            //    .setParameter("login", login)
            //    .getResultList();


    }
}
