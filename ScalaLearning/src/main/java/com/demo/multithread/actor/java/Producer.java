package com.demo.multithread.actor.java;

/**
 * Created by a549238 on 1/16/14.
 */
public class Producer implements Runnable {

    private Drop drop;

    private String[] importInfo = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
    };

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {

        for (String message : importInfo)
            drop.put(message);
        drop.put("DONE");
    }
}
