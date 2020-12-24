package com.ishan.javabasics.paralleANDasync;

import java.util.concurrent.CompletableFuture;

public class CompletableFuturesExample {

  public static void main(String[] args) throws InterruptedException {

    getId()
        .thenCompose(CompletableFuturesExample::getName)
        .thenAccept((value) -> System.out.println(value + Thread.currentThread()));

    Thread.sleep(5000);
  }

  public static CompletableFuture<Integer> getId() {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("getId " + Thread.currentThread());
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 2;
    });
  }

  public static CompletableFuture<String> getName(Integer id) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("getName " + Thread.currentThread());
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "Id" + id + ", Ishan Soni";
    });
  }

}