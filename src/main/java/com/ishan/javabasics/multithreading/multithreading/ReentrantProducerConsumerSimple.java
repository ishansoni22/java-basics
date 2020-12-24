package com.ishan.javabasics.multithreading.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantWorker {

  private Lock lock = new ReentrantLock(true);
  private Condition condition = lock.newCondition();

  public void produce() throws InterruptedException {
    lock.lock();
    System.out.println("PriorityBlockingQueueExampleConsumer | producing values");
    Thread.sleep(5000);
    condition.await();
    System.out.println("PriorityBlockingQueueExampleConsumer | finished");
    lock.unlock();
  }

  public void consume() throws InterruptedException {
    lock.lock();
    System.out.println("Consumer | consuming values");
    Thread.sleep(5000);
    condition.signal();
    lock.unlock();
  }
}

public class ReentrantProducerConsumerSimple {

  public static void main(String[] args) {
    ReentrantWorker worker = new ReentrantWorker();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          worker.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          worker.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();

  }

}
