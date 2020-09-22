package com.ishan.javabasics.multithreading.sequential;

class Runner1 {

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Runner1 " + i);
    }
  }
}

class Runner2 {

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Runner2 " + i);
    }
  }
}

public class Sequential {

  public static void main(String[] args) {
    Runner1 runner1 = new Runner1();
    Runner2 runner2 = new Runner2();
    runner1.run();
    runner2.run();
  }

}
