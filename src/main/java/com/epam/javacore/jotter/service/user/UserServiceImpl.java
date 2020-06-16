package com.epam.javacore.jotter.service.user;

import com.epam.javacore.jotter.dao.user.UserDao;
import com.epam.javacore.jotter.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User selectUser(long id) {
        return userDao.selectUser(id);
    }

    @Override
    public User selectUser(String login) {
        return userDao.selectUser(login);
    }

    @Override
    public List<User> selectUserList(String login, String searchLogin){
        return userDao.selectUserList(login, searchLogin);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }
}
