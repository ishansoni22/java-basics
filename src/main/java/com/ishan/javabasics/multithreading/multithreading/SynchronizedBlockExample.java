package com.ishan.javabasics.multithreading.multithreading;

public class SynchronizedBlockExample {

  private static int counter1 = 0;
  private static int counter2 = 0;

  private static Object lock1 = new Object();
  private static Object lock2 = new Object();

  private static void compute() {

    for (int i = 0; i < 10000; i++) {
      addCounter1();
      addCounter2();
    }

  }

  /**
   * If you use synchronized with methods, whenever a thread calls this method, the other threads
   * cannot call any other synchronised methods because it uses the class intrinsic lock. In our
   * case, it should have been perfectly fine since both methods work on different resources
   */
  private static void addCounter1() {
    synchronized (lock1) {
      counter1++;
    }

  }

  private static void addCounter2() {
    synchronized (lock2) {
      counter2++;
    }
  }

  public static void main(String[] args) {

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        compute();
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        compute();
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

    System.out.println(counter1);
    System.out.println(counter2);
  }

}
