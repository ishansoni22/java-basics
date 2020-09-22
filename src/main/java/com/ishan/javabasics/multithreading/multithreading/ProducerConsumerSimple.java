package com.ishan.javabasics.multithreading.multithreading;

import java.util.Stack;

class ProducerConsumerSimpleProcessor {

  private Stack<Integer> values = new Stack();

  void produce() throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      synchronized (this) {
        Thread.sleep(2000);
        values.push(i);
        System.out.println("Produced -> " + i);
        wait();
        notify();
      }
    }
  }

  void consume() throws InterruptedException {
    Thread.sleep(1000);
    for (int i = 0; i < 10; i++) {
      synchronized (this) {
        Integer value = values.pop();
        Thread.sleep(2000);
        System.out.println("Consumed -> " + value);
        notify();
        wait();
      }
    }
  }
}

public class ProducerConsumerSimple {

  public static void main(String[] args) {

    ProducerConsumerSimpleProcessor producerConsumerSimpleProcessor = new ProducerConsumerSimpleProcessor();

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          producerConsumerSimpleProcessor.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          producerConsumerSimpleProcessor.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();
  }

}