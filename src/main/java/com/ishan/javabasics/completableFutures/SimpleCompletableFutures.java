package com.ishan.javabasics.completableFutures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleCompletableFutures {

  public static void main(String[] args) {
    Client client = new Client();
    CompletableFuture<String> personScorePipeline
        = new CompletableFuture<>();

    personScorePipeline
        .thenApply(client::getPerson)
        .thenApply(client::getScore)
        .thenAccept(creditScore -> System.out.println(creditScore.getScore()));

    ExecutorService executor =
        Executors.newSingleThreadExecutor();

    executor.submit(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      personScorePipeline.complete("12345");
    });

    executor.shutdown();
    System.out.println("Done!");

  }
}
