package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leha on 2017-03-24.
 */
public class Controller {
    static Model model = new Model();
    public List<String> onDataListShow() {
        return model.getStringDataList();
    }


}
