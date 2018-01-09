package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.List;

/**
 * Created by leha on 2017-05-30.
 */
public interface Model {
    ModelData getModelData();
   public void loadUsers();
    public void loadDeletedUsers();
    public void loadUserById(long userId);
    public void deleteUserById(long id);
    public void changeUserData(String name, long id, int level);

}
