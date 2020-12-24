package com.ishan.javabasics.multithreading.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

  private BlockingQueue<Integer> queue;

  Producer(BlockingQueue<Integer> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    int value = 0;
    while (true) {
      System.out.println("Producing " + value);
      try {
        Thread.sleep(10);
        /** Inserts the specified element into this queue, waiting if necessary
         * for space to become available. **/
        this.queue.put(value);
        value++;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Consumer implements Runnable {

  private BlockingQueue<Integer> queue;

  Consumer(BlockingQueue<Integer> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      /** Retrieves and removes the head of this queue, waiting if necessary
       until an element becomes available **/
      try {
        Integer value = this.queue.take();
        System.out.println("Consuming " + value);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class BlockingQueueExample {

  public static void main(String[] args) {

    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    Thread t1 = new Thread(new PriorityBlockingQueueExampleConsumer(queue));
    Thread t2 = new Thread(new Consumer(queue));

    t1.start();
    t2.start();

  }
}
