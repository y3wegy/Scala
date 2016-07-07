package com.demo.multithread.actor.java;

/**
 * Created by a549238 on 1/16/14.
 */
public class Consumer implements Runnable {

    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take())
            System.out.format("Receive message  %s %n", message);
        String message;
        while(true)
        {
            message =drop.take();
            while (message==null)
            {
                message =drop.take();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(message.equals("DONE"))
                break;
            System.out.format("Receive message %n %s",message );
            message=drop.take();
        }
    }
}
