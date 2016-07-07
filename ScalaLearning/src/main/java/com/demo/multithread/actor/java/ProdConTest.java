package com.demo.multithread.actor.java;

import org.junit.Test;

/**
 * Created by a549238 on 1/16/14.
 */
public class ProdConTest {
    @Test
    public void testProdCon() {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
