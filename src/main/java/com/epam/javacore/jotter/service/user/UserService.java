package com.epam.javacore.jotter.service.user;

import com.epam.javacore.jotter.domain.user.User;

import java.util.List;

public interface UserService {

     void addUser(User user);

     void updateUser(User user);

     User selectUser(long id);

     User selectUser(String login);

     List<User> selectUserList(String login, String searchLogin);

     void deleteUser(long id);


}
