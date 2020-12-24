package com.ishan.javabasics.completableFutures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LoanClient {

  public CompletableFuture<Person> getPerson(String pan) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread());
      Person person = null;
      try {
        //Mock a call
        Thread.sleep(1000);
        person = new Person();
        person.setFirstName("Ishan");
        person.setLastName("Soni");
        person.setPan(pan);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return person;
    });
  }

  public CompletableFuture<CreditScore> getScore(Person person) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread());
      CreditScore score = null;
      try {
        //Mock a call
        Thread.sleep(2000);
        score = new CreditScore();
        score.setPan(person.getPan());
        score.setScore(750);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return score;
    });
  }

  public CompletableFuture<List<LoanDetails>> getLoanDetails(Person person) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread());
      List<LoanDetails> loans = new ArrayList<>();
      try {
        //Mock a call
        Thread.sleep(2000);
        LoanDetails loan = new LoanDetails();
        loan.setBank("Yes bank");
        loan.setAmount(100000d);
        loan.setPaid(false);
        loans.add(loan);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return loans;
    });
  }

  public EligibleLoan getEligibility(CreditScore score, List<LoanDetails> loans) {
    EligibleLoan loan = new EligibleLoan();
    loan.setEligible(false);
    return loan;
  }

  public CompletableFuture<EligibleLoan> getEligibilityAsync(CreditScore score,
      List<LoanDetails> loans) {
    return CompletableFuture.supplyAsync(() -> {
      EligibleLoan loan = new EligibleLoan();
      loan.setEligible(false);
      return loan;
    });
  }

  public static class Person {

    private String firstName;
    private String lastName;
    private String pan;

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getPan() {
      return pan;
    }

    public void setPan(String pan) {
      this.pan = pan;
    }
  }

  public static class CreditScore {

    private String pan;
    private Integer score;

    public String getPan() {
      return pan;
    }

    public void setPan(String pan) {
      this.pan = pan;
    }

    public Integer getScore() {
      return score;
    }

    public void setScore(Integer score) {
      this.score = score;
    }
  }

  public static class LoanDetails {

    private String bank;
    private Double amount;
    private boolean paid;

    public String getBank() {
      return bank;
    }

    public void setBank(String bank) {
      this.bank = bank;
    }

    public Double getAmount() {
      return amount;
    }

    public void setAmount(Double amount) {
      this.amount = amount;
    }

    public boolean isPaid() {
      return paid;
    }

    public void setPaid(boolean paid) {
      this.paid = paid;
    }
  }

  public static class EligibleLoan {

    private boolean eligible;
    private Double amount;

    public boolean isEligible() {
      return eligible;
    }

    public void setEligible(boolean eligible) {
      this.eligible = eligible;
    }

    public Double getAmount() {
      return amount;
    }

    public void setAmount(Double amount) {
      this.amount = amount;
    }

    @Override
    public String toString() {
      return "EligibleLoan{" +
          "eligible=" + eligible +
          ", amount=" + amount +
          '}';
    }
  }

}