package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {

        for (Thread x: threads) {
            Thread.State state = x.getState();
            switch (state) {
                case NEW: {
                    x.start();
                    continue;
                }
                case WAITING: {
                    x.interrupt();
                    continue;
                }
                case TIMED_WAITING: {
                    x.interrupt();
                    continue;
                }
                case BLOCKED: {
                    x.interrupt();
                    continue;
                }
                case RUNNABLE: {
                    if (x.isInterrupted()) {
                    }
                    continue;
                }
                case TERMINATED: System.out.println(x.getPriority());
            }
        }



     /*   1. Если нить еще не запущена, то запусти ее.
        2. Если нить в ожидании, то прерви ее.
        3. Если нить работает, то проверь маркер isInterrupted.
        4. Если нить прекратила работу, то выведи в консоль ее приоритет.
                Используй switch.   */
    }

    public static void main(String[] args) {
    }
}
