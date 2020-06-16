package com.epam.javacore.jotter.service.subscriber;

import com.epam.javacore.jotter.dao.user.UserDao;
import com.epam.javacore.jotter.dao.subscribe.SubscribeDao;
import com.epam.javacore.jotter.domain.user.Subscriber;
import com.epam.javacore.jotter.domain.user.User;
import com.epam.javacore.jotter.enums.Status;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubscribeServiceImpl implements  SubscribeService {

    private SubscribeDao subscribeDao;
    private UserDao userDao;
    public SubscribeServiceImpl(SubscribeDao subscribeDao, UserDao userDao){
        this.subscribeDao=subscribeDao;
        this.userDao=userDao;
    }

    @Override
    public void addSubscriber(long id, String login) {
        Subscriber subscriber=new Subscriber();
        User userSubscriber=userDao.selectUser(login);
        User user=userDao.selectUser(id);
        subscriber.setUser(user);
        subscriber.setSubscriber(userSubscriber);
        subscriber.setStatus(Status.WAITING);
        subscribeDao.addSubscriber(subscriber);
    }

    @Override
    public List<Subscriber> selectSubscriberList(String login) {
        List<Subscriber> subscriberList=subscribeDao.selectSubscriberList(login);
        for(Subscriber s: subscriberList){
            s.getSubscriber();
            System.out.println("login subs:" +s.getSubscriber().getLogin());
            System.out.println("login user:" +s.getUser().getLogin());
        }
        return subscriberList;
    }

    @Override
    public List<Subscriber> selectSubscriptionList(String login) {
        List<Subscriber> list=subscribeDao.selectSubscriptionList(login);
        for(Subscriber s: list){
            s.getSubscriber();
        }

        return list;
    }


    @Override
    public void acceptSubscriber(long id) {
        subscribeDao.acceptSubscriber(id);
    }

    @Override
    public void deleteSubscriber(long id) {
        subscribeDao.deleteSubscriber(id);
    }
}
