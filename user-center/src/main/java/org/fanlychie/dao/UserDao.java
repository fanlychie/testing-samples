package org.fanlychie.dao;

import org.fanlychie.model.User;

public interface UserDao {

    /**
     * 调用持久层存储用户信息
     * @param username
     * @param password
     * @return
     */
    boolean add(String username, String password);

    /**
     * 调用持久层查询用户信息
     * @param username
     * @return
     */
    User getByName(String username);

}