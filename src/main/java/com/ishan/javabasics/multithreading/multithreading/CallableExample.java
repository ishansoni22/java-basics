package com.ishan.javabasics.multithreading.multithreading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CallableExampleWorker implements Callable<String> {

  private Long id;
  private Random random = new Random();

  public CallableExampleWorker(Long id) {
    this.id = id;
  }

  @Override
  public String call() throws Exception {
    int interval = 1000 + random.nextInt(5000);
    Thread.sleep(interval);
    return "Id: " + this.id.toString();
  }
}

public class CallableExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      String id = executorService
          .submit(new CallableExampleWorker(new Long(i)))
          //Will wait for this thread to execute before picking up another thread!
          /*Waits if necessary for the computation to complete, and then
           * retrieves its result.*/
          .get();
      System.out.println(id);
    }
  }

}
