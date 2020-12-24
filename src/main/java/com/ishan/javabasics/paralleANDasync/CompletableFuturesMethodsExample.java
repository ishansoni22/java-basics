package com.ishan.javabasics.paralleANDasync;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFuturesMethodsExample {

  public static void main(String[] args) throws InterruptedException {
    log("User with Id: dfergg-rgrsgf-dsfasd Disconnected")
        .handle((data, throwable) -> {
          System.out.println(data);
          System.out.println(throwable);
          return 0;
        });
    Thread.sleep(100);
  }

  public static CompletableFuture<Void> log(String log) {
    return CompletableFuture.runAsync(() -> {
      Random random = new Random(0);
      int number = random.nextInt(2);
      System.out.println(number);
      switch (number) {
        case 0:
          System.out.println("Logging: " + log);
          break;
        case 1:
          throw new IllegalArgumentException("Something bad happened");
      }
    });
  }

}