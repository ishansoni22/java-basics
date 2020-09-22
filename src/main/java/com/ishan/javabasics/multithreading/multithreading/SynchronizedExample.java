package com.ishan.javabasics.multithreading.multithreading;

public class SynchronizedExample {

  /**
   * Incrementing an integer is not an atomic operation
   * <p>
   * Thread 1 gets the value of counter (0), Thread 2 gets the value of counter (0), Thread 1
   * increments it (1), Thread 2 increments it (1) ----- Should be 2
   */
  private static int counter = 0;

  public static void process() throws InterruptedException {

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10000; i++) {
          //Incorrect Computation
          //counter = counter + 1;
          increment();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10000; i++) {
          //Incorrect Computation
          //counter = counter + 1;
          increment();
        }
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();
    System.out.println(counter);

  }

  private synchronized static void increment() {
    counter = counter + 1;
  }

  public static void main(String[] args) throws InterruptedException {
    process();
  }

}
