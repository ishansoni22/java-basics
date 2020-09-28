package com.ishan.javabasics.multithreading.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableAndFutureExampleWorker implements Callable<String> {

  private Long id;
  private Random random = new Random();

  public CallableAndFutureExampleWorker(Long id) {
    this.id = id;
  }

  @Override
  public String call() throws Exception {
    int interval = 1000 + random.nextInt(5000);
    Thread.sleep(interval);
    return "Id: " + this.id.toString();
  }
}

public class CallableAndFutureExample {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    List<Future> ids = new ArrayList<>();
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      //Will not wait here!
      ids.add(executorService
          .submit(new CallableAndFutureExampleWorker(new Long(i + 1))));
    }

    for (Future id : ids) {
      //Will wait here!
      System.out.println(id.get());
    }

    //You cannot spawn any new threads
    //Will not interrupt threads that have already spawned
    executorService.shutdown();
  }

}
