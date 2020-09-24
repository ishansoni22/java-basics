package com.ishan.javabasics.multithreading.multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

  private ReentrantLock lock = new ReentrantLock(true);
  private int counter = 0;

  public void increment() {
    lock.lock();
    ++counter;
    lock.unlock();
  }

  public void decrement() {
    lock.lock();
    --counter;
    lock.unlock();
  }

  public static void main(String[] args) {
    ReentrantExample reentrantExample = new ReentrantExample();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100000; i++) {
          reentrantExample.increment();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100000; i++) {
          reentrantExample.decrement();
        }
      }
    });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(reentrantExample.counter);

  }

}
