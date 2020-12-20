package org.fanlychie.service;

import org.fanlychie.dao.UserDao;
import org.fanlychie.model.User;

public class UserService {

    private UserDao userDao;

    private MessageService messageService;

    public boolean register(String username, String password) {
        if (username != null && username.length() > 0 && password != null && password.length() > 0) {
            boolean success = userDao.add(username, password);
            if (success) {
                messageService.send("注册成功");
            }
            return success;
        }
        return false;
    }

    public User query(String username) {
        if (username != null && username.length() > 0) {
            return userDao.getByName(username);
        }
        return null;
    }

}