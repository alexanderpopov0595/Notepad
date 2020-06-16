package com.epam.javacore.jotter.exceptions;

import com.epam.javacore.jotter.domain.user.User;

public class LoginIsCurrentlyExisting extends RuntimeException {
    private final User user;

    public LoginIsCurrentlyExisting(User user){
        this.user=user;
    }
    /**
     * Method returns error message
     * @return error message
     */
    @Override
    public String getMessage(){
        return "Login is currently existing";
    }

    public User getUser(){
        return user;
    }

}