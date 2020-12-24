package com.ishan.javabasics.completableFutures;

import com.ishan.javabasics.completableFutures.Client.Person;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionHandling {

  public static void main(String[] args) {
    Client client = new Client();
    get("12345")
        .exceptionally(e -> new Person()).thenApply(client::getScore)
        .thenAccept(creditScore -> System.out.println(creditScore.getScore()));
  }

  public static CompletableFuture<Person> get(String pan) {
    return CompletableFuture.supplyAsync(() -> {
      throw new RuntimeException();
    });
  }

}
