package com.ishan.javabasics.multithreading.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ProducerConsumerProcessor {

  private List<Integer> values = new ArrayList<>();
  private final Object lock = new Object();
  private static final int UPPER_LIMIT = 10;
  private static final int LOWER_LIMIT = 0;
  private Random random = new Random(0);

  void produce() throws InterruptedException {
    synchronized (lock) {
      while (true) {
        if (values.size() >= UPPER_LIMIT) {
          System.out.println("PriorityBlockingQueueExampleConsumer | Limit reached. Stopping producing values...");
          lock.wait();
        } else {
          Thread.sleep(1000);
          values.add(random.nextInt());
          //Call notify to tell other threads, if you want to do something with the produced values, you can
          //This won't work since there is code after this thread -> the while loop
          //It will not give the lock up until the wait() condition is called
          lock.notify();
        }
      }
    }
  }

  void consume() throws InterruptedException {
    //Sleep to make sure the producer acquires the lock first
    Thread.sleep(100);
    synchronized (lock) {
      while (true) {
        if (values.size() > LOWER_LIMIT) {
          Thread.sleep(1500);
          System.out.println("Consumer | consuming " + values.remove(values.size() - 1));
          /**
           * Why does it not notify the producer to produce more values?
           * Because there code after notify -> It is the while loop
           *
           */
          lock.notify();
        } else {
          lock.wait();
        }
      }
    }
  }
}

public class ProducerConsumer {

  public static void main(String[] args) {

    ProducerConsumerProcessor producerConsumerProcessor = new ProducerConsumerProcessor();

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          producerConsumerProcessor.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          producerConsumerProcessor.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();
  }

}