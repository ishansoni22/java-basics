package com.ishan.javabasics.multithreading.multithreading;

class Worker1 implements Runnable {

  public boolean isTerminated() {
    return isTerminated;
  }

  public void setTerminated(boolean terminated) {
    isTerminated = terminated;
  }

  /**
   * It may sometime happen that the CPU caches this variable When the main Thread tries to set the
   * isTerminated value to true, this cache value is not updated This thread may continue to run
   * forever!
   */
  private boolean isTerminated = false;

  @Override
  public void run() {
    while (!isTerminated) {
      System.out.println("Hello from Worker1 Thread");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class VolatileExample {

  public static void main(String[] args) throws InterruptedException {
    Worker1 worker1 = new Worker1();
    Thread t1 = new Thread(worker1);
    t1.start();

    Thread.sleep(1000);

    worker1.setTerminated(true);

  }

}
