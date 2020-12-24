package com.ishan.javabasics.completableFutures;

import com.ishan.javabasics.completableFutures.LoanClient.CreditScore;
import com.ishan.javabasics.completableFutures.LoanClient.EligibleLoan;
import com.ishan.javabasics.completableFutures.LoanClient.LoanDetails;
import com.ishan.javabasics.completableFutures.LoanClient.Person;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureThenCombine {

  public static void main(String[] args) throws InterruptedException {
    LoanClient client = new LoanClient();

    CompletableFuture<Person> person = client
        .getPerson("12345");

    CompletableFuture<CreditScore> score = person
        .thenCompose(client::getScore);

    CompletableFuture<List<LoanDetails>> loans = person
        .thenCompose(client::getLoanDetails);

    CompletableFuture<EligibleLoan> loanDetail = score
        .thenCombine(loans, client::getEligibility);

    /*CompletableFuture<CompletableFuture<EligibleLoan>> loanDetailAsync = score
        .thenCombine(loans, (s, l) -> client.getEligibilityAsync(s, l));*/

    loanDetail.thenAccept(System.out::println);

    Thread.sleep(5000);

  }

}
