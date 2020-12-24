package com.ishan.javabasics.completableFutures;

public class CompletableFutureThenCompose {

  public static void main(String[] args) throws InterruptedException {
    Client client = new Client();

    client.getPersonAsync("12345")
        .thenCompose(client::getScoreAsync)
        .thenAccept(creditScore -> System.out.println(creditScore.getScore()));

    Thread.sleep(5000);
  }

}