package com.epam.javacore.jotter.dao.subscribe;

import com.epam.javacore.jotter.domain.user.Subscriber;
import com.epam.javacore.jotter.domain.user.User;

import java.util.List;

public interface SubscribeDao {

    void addSubscriber(Subscriber subscriber);

    void acceptSubscriber(long id);

    void deleteSubscriber(long id);

    List<Subscriber> selectSubscriberList(String login);

    List<Subscriber> selectSubscriptionList(String login);


}
