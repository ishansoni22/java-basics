package com.ishan.javabasics.multithreading1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class IsReduceParallel {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    //Available processors on my machine
    System.out.println(Runtime.getRuntime().availableProcessors());
    //No of threads the common FJP has
    System.out.println(ForkJoinPool.commonPool());

    numbers
        .parallelStream()
        .reduce(0, (a, b) -> add(a, b));
  }

  private static Integer add(Integer a, Integer b) {
    Integer result = a + b;
    System.out.println("a: " + a + " b: " + b + " r:" + result + " t" + Thread.currentThread());
    return result;
  }

}