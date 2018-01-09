package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        boolean  flag = false;


        Thread thread3 = new Thread() {
            @Override
            public void run() {
                synchronized (o2){
                    System.out.println("thread3 нить блокирует О2");
                    try {
                        Thread.sleep(50);
                    }
                    catch (InterruptedException e) {}
                    System.out.println("thread3 нить ОТПУСКАЕТ О2");
                }
            }
        };
       Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(10);
                }
                catch (InterruptedException e) {}
                synchronized (o1){
                    System.out.println("нить 1 блокирует О1");
                    try {
                        sleep(100);
                    }
                    catch (InterruptedException e) {}
                    System.out.println("нить 1 отпускает О1");
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5);
                }
                catch (InterruptedException e) {}
                System.out.println("нить 2 запускает и блокирует по возможности о1 и о2");
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(55);
        }
        catch (InterruptedException e) {}

        if (thread2.getState() != Thread.State.BLOCKED) return true;

Thread.sleep(1000);
        return flag;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
