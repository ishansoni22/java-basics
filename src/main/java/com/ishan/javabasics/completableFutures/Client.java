package com.ishan.javabasics.completableFutures;

import java.util.concurrent.CompletableFuture;

public class Client {

  public Person getPerson(String pan) {
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
  }

  public CompletableFuture<Person> getPersonAsync(String pan) {
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

  public CreditScore getScore(Person person) {
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
  }

  public CompletableFuture<CreditScore> getScoreAsync(Person person) {
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

}