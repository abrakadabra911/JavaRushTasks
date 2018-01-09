package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
       public static AtomicInteger factory = new AtomicInteger(0);
        public  AtomicInteger threadNumber = new AtomicInteger(0);
        int numberOfFactory=0;
        public AmigoThreadFactory() {
            numberOfFactory = factory.incrementAndGet();
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread output = new Thread(r);
            output.setDaemon(false);
            output.setPriority(5);
            String group = output.getThreadGroup().getName();
            output.setName(String.format("%s-pool-%s-thread-%s",group,numberOfFactory,threadNumber.incrementAndGet()));
            return output;
        }
    }

}
        /* example output
        secondGroup-pool-2-thread-1
        firstGroup-pool-1-thread-1
        firstGroup-pool-1-thread-3
        secondGroup-pool-2-thread-3*/
