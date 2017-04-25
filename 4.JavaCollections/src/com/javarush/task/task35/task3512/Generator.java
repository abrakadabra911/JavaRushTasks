package com.javarush.task.task35.task3512;

public class Generator<T> {
    T createContents(Class<T> clazz) throws Exception
    {
        return clazz.newInstance();
    }

    T newInstance() {

        return createContents(T.class) ;
    }

}
