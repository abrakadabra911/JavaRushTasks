package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;
    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 9; i++){
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                System.out.format("Элемент 'ShareItem-%d' добавлен%n", i);
                this.queue.offer(new ShareItem("ShareItem-" + i, i));
                Thread.sleep(100);
                if (this.queue.hasWaitingConsumer())
                    System.out.format("Consumer в ожидании!");
            }
        } catch (InterruptedException e) {
        }
    }
}