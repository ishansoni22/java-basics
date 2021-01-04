package com.ishan.javabasics.multithreading1;

import java.util.Arrays;

public class ImperativeVsFunctional {

  public static void main(String[] args) {
    //Imperative Programming
    //You tell exactly what to do and how to do it
    //Very hard to parallelize
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int sum = 0;
    for (int i : arr) {
      if (i % 2 == 0) {
        sum += i;
      }
    }

    System.out.println(sum);

    //Functional Programming - functional composition
    //Collection Pipeline pattern
    //get a stream - Is an iterator on auto-pilot
    //functional style has less complexity and is easier to parallelize
    int funcSum = Arrays.stream(arr)
        .filter(i -> i % 2 == 0)
        .sum();

    System.out.println(funcSum);
  }
}