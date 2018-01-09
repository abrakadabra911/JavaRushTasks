package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leha on 2017-07-08.
 */
public class MyThread extends Thread {

   static AtomicInteger x = new AtomicInteger(0);

    public MyThread() {
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
        y = x.incrementAndGet();}
        this.setPriority(y);
    }

    public MyThread(Runnable target) {
        super(target);
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
            y = x.incrementAndGet();}
        this.setPriority(y);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
            y = x.incrementAndGet();}
        this.setPriority(y);
    }

    public MyThread(String name) {
        super(name);
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
            y = x.incrementAndGet();}
        this.setPriority(y);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
            y = x.incrementAndGet();}
        this.setPriority(y);
}

    public MyThread(Runnable target, String name) {
        super(target, name);
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
            y = x.incrementAndGet();}
        this.setPriority(y);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
            y = x.incrementAndGet();}
        this.setPriority(y);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        int y = x.incrementAndGet();
        if(y>10){ x= new AtomicInteger(0);
            y = x.incrementAndGet();}
        this.setPriority(y);
    }
}
