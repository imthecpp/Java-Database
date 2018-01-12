package com.dave.api;

import com.dave.model.User;

import java.util.Set;

public interface IUserDao {

    User getUser(int id);
    Set<User> getAllUsers();
    User getUserByUserNameAndPassword(String name, String password);

    boolean insertUser(String name, String password, int age);
    boolean updateUser(String name, String pass, String nName, String nPass);
    boolean deleteUser(int id);

}
