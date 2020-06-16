package com.epam.javacore.jotter.controller;

import com.epam.javacore.jotter.service.subscriber.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/subscribers")
public class SubscribeController {

    private SubscribeService subscribeService;

    @Autowired
    public SubscribeController(SubscribeService subscribeService){
        this.subscribeService=subscribeService;

    }

    @GetMapping("/addSubscriber/{id}")
    public String addSubscriber(@PathVariable long id, Principal principal){
        subscribeService.addSubscriber(id, principal.getName());
        return "redirect:/subscribers/subscriptions";
    }

    @GetMapping()
    public String showSubscriberList(Principal principal, Model model){
        model.addAttribute("subscriberList", subscribeService.selectSubscriberList(principal.getName()));
        return "subscribers/list";
    }

    @GetMapping("/acceptSubscriber/{id}")
    public String acceptSubscriber(@PathVariable long id){
        subscribeService.acceptSubscriber(id);
        return "redirect:/subscribers";
    }

    @GetMapping("/deleteSubscriber/{id}")
    public String deleteSubscriber(@PathVariable long id){
        subscribeService.deleteSubscriber(id);
        return "redirect:/subscribers";
    }

    @GetMapping("/subscriptions")
    public String showSubscriptions(Model model, Principal principal){
        model.addAttribute("subscriptionList", subscribeService.selectSubscriptionList(principal.getName()));
       return "subscribers/subscriptionlist";

    }


}
