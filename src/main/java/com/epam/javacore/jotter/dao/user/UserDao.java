package com.epam.javacore.jotter.dao.user;

import com.epam.javacore.jotter.domain.user.User;

import java.util.List;

public interface UserDao {

    public void addUser(User user);

    public void updateUser(User user);

    public User selectUser(long id);

    public User selectUser(String login);

    public List<User> selectUserList(String login, String searchLogin);

    public void deleteUser(long id);
}
