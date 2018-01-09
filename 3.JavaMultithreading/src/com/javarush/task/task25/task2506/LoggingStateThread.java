package com.javarush.task.task25.task2506;

/**
 * Created by leha on 2017-06-04.
 */
public class LoggingStateThread extends Thread{
       private Thread thread = null;
        private State state = null;

        public LoggingStateThread (Thread thread) {
            this.thread = thread;
            state = thread.getState();
            System.out.println(state);
        }

        @Override
        public void run() {
            while(!state.equals(State.TERMINATED))
            if(!state.equals(thread.getState())) {
                System.out.println(thread.getState());
                state = thread.getState();
            }
            this.interrupt();
        }

}
