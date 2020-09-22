package com.ishan.javabasics.multithreading.multithreading;

class Runner1 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; ++i) {
      System.out.println("Runner1 " + i);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Runner2 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; ++i) {
      System.out.println("Runner2 " + i);
    }
  }
}

class Runner3 extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 10; ++i) {
      System.out.println("Runner3 " + i);
    }
  }
}

public class MultiThreaded {

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new Runner1());
    Thread t2 = new Thread(new Runner2());
    Thread t3 = new Runner3();

    t1.start();
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();

    System.out.println("All tasks completed!");

  }

}
