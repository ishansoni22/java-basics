package com.ishan.javabasics.multithreading.multithreading;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class PriorityBlockingQueueExampleProducer implements Runnable {

  private BlockingQueue<Integer> queue;

  PriorityBlockingQueueExampleProducer(BlockingQueue<Integer> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      queue.put(10);
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Random random = new Random(0);
    while (true) {
      try {
        queue.put(random.nextInt(10000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}


class PriorityBlockingQueueExampleConsumer implements Runnable {

  private BlockingQueue<Integer> queue;

  PriorityBlockingQueueExampleConsumer(BlockingQueue<Integer> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Integer element = queue.take();
        System.out.println("Consumed: " + element);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class PriorityBlockingQueueExample {

  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
    Thread t1 = new Thread(new PriorityBlockingQueueExampleProducer(queue));
    Thread t2 = new Thread(new PriorityBlockingQueueExampleConsumer(queue));

    t1.start();
    t2.start();

  }
}
