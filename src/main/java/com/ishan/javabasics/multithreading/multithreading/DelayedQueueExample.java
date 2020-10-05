package com.ishan.javabasics.multithreading.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedMessage implements Delayed {

  private Long duration;

  public Long getDuration() {
    return this.duration;
  }

  DelayedMessage(Long duration) {
    this.duration = System.currentTimeMillis() + duration;
  }

  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(this.duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed other) {
    return this.getDuration().compareTo(((DelayedMessage) other).getDuration());
  }
}


public class DelayedQueueExample {

  public static void main(String[] args) throws InterruptedException {

    BlockingQueue queue = new DelayQueue();
    queue.put(new DelayedMessage(new Long(1000)));
    queue.put(new DelayedMessage(new Long(5000)));

    while (queue.size() > 0) {
      Object obj = queue.take();
      System.out.println(obj);

    }
  }

}
