package com.epam.javacore.jotter.service.subscriber;

import com.epam.javacore.jotter.domain.user.Subscriber;
import com.epam.javacore.jotter.domain.user.User;

import java.util.List;

public interface SubscribeService {

    void addSubscriber(long id, String login);

    List<Subscriber> selectSubscriberList(String login);

    List<Subscriber> selectSubscriptionList(String login);

    void acceptSubscriber(long id);

    void deleteSubscriber(long id);
}
