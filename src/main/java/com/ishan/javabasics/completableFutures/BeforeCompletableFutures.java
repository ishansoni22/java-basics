package com.ishan.javabasics.completableFutures;

import com.ishan.javabasics.completableFutures.Client.CreditScore;
import com.ishan.javabasics.completableFutures.Client.Person;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BeforeCompletableFutures {

  public static void main(String[] args) throws Exception {
    Client client = new Client();
    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Person> personFuture =
        executorService.submit(() -> client.getPerson("12345"));
    //Blocking
    Person person = personFuture.get();
    Future<CreditScore> scoreFuture =
        executorService.submit(() -> client.getScore(person));
    //Blocking
    CreditScore score = scoreFuture.get();
    System.out.println(score.getScore());
  }

}