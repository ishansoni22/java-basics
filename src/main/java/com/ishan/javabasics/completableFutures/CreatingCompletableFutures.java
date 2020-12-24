package com.ishan.javabasics.completableFutures;

import com.ishan.javabasics.completableFutures.Client.Person;
import java.util.concurrent.CompletableFuture;

public class CreatingCompletableFutures {

  public static void main(String[] args) throws InterruptedException {
    Client client = new Client();
    get("12345")
        .thenApply(client::getScore)
        .thenAccept(score -> System.out.println(score.getScore()));
    System.out.println(Thread.currentThread() + "Preparing to sleep for some time");
    Thread.sleep(5000);
  }

  public static CompletableFuture<Person> get(String pan) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (Exception e) {

      }
      Person person = new Person();
      person.setPan(pan);
      person.setFirstName("Ishan");
      person.setLastName("Soni");
      return person;
    });
  }

}
