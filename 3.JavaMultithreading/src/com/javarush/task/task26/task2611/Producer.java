package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
//
/**
 * Created by leha on 2017-06-16.
 */
public class Producer implements Runnable  {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
      try {
          int i = 0;
          while (!currentThread.isInterrupted()) {
              i++;
              map.put(String.valueOf(i), "Some text for " + i);
              Thread.sleep(300);
          }
    } catch (Exception e) {
        System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
    }
    }
}
