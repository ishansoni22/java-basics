package com.ishan.javabasics.multithreading.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CyclicBarrierExampleWorker1 implements Callable<String> {

  private CyclicBarrier barrier;

  CyclicBarrierExampleWorker1(CyclicBarrier barrier) {
    this.barrier = barrier;
  }

  @Override
  public String call() throws Exception {
    System.out.println("Worker1 | Started");
    Thread.sleep(2000);
    barrier.await();
    System.out.println("Worker1 | Finished");
    return null;
  }
}

class CyclicBarrierExampleWorker2 implements Callable<String> {

  private CyclicBarrier barrier;

  CyclicBarrierExampleWorker2(CyclicBarrier barrier) {
    this.barrier = barrier;
  }

  @Override
  public String call() throws Exception {
    System.out.println("Worker2 | Started");
    Thread.sleep(10000);
    barrier.await();
    System.out.println("Worker2 | Finished");
    return null;
  }
}

public class CyclicBarrierExample {

  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
      @Override
      public void run() {
        System.out.println("All done!");
      }
    });

    //Waiting on each other!
    //Worker1 and Worker2 will wait on each other!
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(new CyclicBarrierExampleWorker1(barrier));
    executorService.submit(new CyclicBarrierExampleWorker2(barrier));

    //barrier.getNumberWaiting();
    //barrier.getParties();
    //barrier.isBroken();
    //barrier.reset();
    //barrier.await();

  }
}
