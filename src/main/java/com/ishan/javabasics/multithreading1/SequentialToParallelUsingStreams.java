package com.ishan.javabasics.multithreading1;

import java.util.Arrays;
import java.util.List;

public class SequentialToParallelUsingStreams {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    numbers
        //.stream()
        .parallelStream()
        .map(SequentialToParallelUsingStreams::transform)
        //.forEach(System.out::println);
        .forEachOrdered(System.out::println);
  }

  private static Integer transform(Integer e) {
    System.out.println("Element: + " + e + ", Thread: " + Thread.currentThread());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    return e * 1;
  }

}