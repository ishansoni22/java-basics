package com.ishan.javabasics.multithreading.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CountDownLatchWorker1 implements Callable<Integer> {

  private CountDownLatch latch;

  CountDownLatchWorker1(CountDownLatch latch) {
    this.latch = latch;
  }

  @Override
  public Integer call() throws Exception {
    Thread.sleep(2000);
    System.out.println("Task 1 done");
    latch.countDown();
    return 1;
  }
}

class CountDownLatchWorker2 implements Callable<String> {

  private CountDownLatch latch;

  CountDownLatchWorker2(CountDownLatch latch) {
    this.latch = latch;
  }

  @Override
  public String call() throws Exception {
    Thread.sleep(10000);
    System.out.println("Task 2 done");
    latch.countDown();
    return "One";
  }
}

public class CountDownLatchExample {

  public static void main(String[] args) {

    CountDownLatch latch = new CountDownLatch(2);

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<Integer> value1 = executorService.submit(new CountDownLatchWorker1(latch));
    Future<String> value2 = executorService.submit(new CountDownLatchWorker2(latch));

    try {
      latch.await();
      System.out.println("Finished, values -> " + value1.get() + ", " + value2.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    } finally {
      executorService.shutdown();
    }
  }

}
