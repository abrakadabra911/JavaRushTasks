package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by leha on 2017-03-02.
 */
public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods someint;
    public CustomInvocationHandler(SomeInterfaceWithMethods someint) {
        this.someint = someint;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println(method.getName() + " in");
      Object result =   method.invoke(someint, args);
        System.out.println(method.getName() + " out");
        return result;
    }

}
