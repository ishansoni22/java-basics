package com.ishan.javabasics.multithreading.multithreading;

class Processor {

  void produce() throws InterruptedException {
    //Takes a class intrinsic lock
    synchronized (this) {
      System.out.println("Produce | Start");
      //Hands over the control of the lock (In this case the class intrinsic lock)
      wait();
      System.out.println("Produce | End");
    }

  }

  void consume() throws InterruptedException {
    //Just to make sure that t1 acquires the intrinsic lock first
    Thread.sleep(1000);
    //Takes a class intrinsic lock
    synchronized (this) {
      System.out.println("Consumer | Start");
      /**
       * Wakes up a single thread that is waiting on this object’s monitor.
       * If any threads are waiting on this object, one of them is chosen to be awakened (The choice is arbitrary)
       * In this case, it wakes up any arbitrary thread that is waiting for the class intrinsic lock(Producer!)
       */
      notify();
      Thread.sleep(5000);
      //Imp - If you have any code after this, it will run anyway and notify will take affect only after that
    }
  }
}

public class WaitAndNotify {

  public static void main(String[] args) {

    Processor processor = new Processor();

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          processor.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          processor.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();
  }

}