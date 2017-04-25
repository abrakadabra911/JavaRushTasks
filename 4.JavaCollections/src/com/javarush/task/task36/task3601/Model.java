package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leha on 2017-03-24.
 */
public class Model {
    static Service service = new Service();
    public List<String> getStringDataList() {
        return service.getData();
    }
}
