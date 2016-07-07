package com.demo.multithread.actor.java;

/**
 * Created by a549238 on 1/16/14.
 */
public class Drop {

    private boolean empty = true;
    private String message;

    private Object lock = new Object();

    public void put(String message) {
        synchronized (lock) {
            while (!empty)
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            empty = false;
            this.message = message;
            lock.notifyAll();
        }
    }

    public String take() {
        synchronized (lock) {
            while (empty)
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            empty = true;
            lock.notifyAll();
            return message;
        }
    }
}
