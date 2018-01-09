package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.FakeModel;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.model.ModelData;

import java.util.List;

/**
 * Created by leha on 2017-05-30.
 */
public class UsersView implements View{
    private Controller controller;
    @Override
    public void refresh(ModelData modelData) {

        List<User> users = modelData.getUsers();

      if(!modelData.isDisplayDeletedUserList()) System.out.println("All users:");
        if(modelData.isDisplayDeletedUserList()){System.out.println("All deleted users:");}
        for(User x: users) {
            System.out.println("\t" + x);
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

   public void fireEventShowAllUsers(){
       controller.onShowAllUsers();
   }
    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }
    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);}


}
