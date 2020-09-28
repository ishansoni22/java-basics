package com.ishan.javabasics.multithreading.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore maintains a set of permits
 * <p>
 * acquire() -> If a permit is available, take it
 * </p>
 * <p>
 * release() -> adds a permit
 * </p>
 * Semaphore just keeps a count of the number available Semaphore(int permits, boolean fair);
 */

/*
Enums are a great way to implement the Singleton pattern
 */
enum Downloader {

  INSTANCE;

  private Semaphore semaphore = new Semaphore(3, true);

  public void Download() {
    try {
      semaphore.acquire();
      download();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }

  }

  private void download() {
    System.out.println("Downloading content...");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class SemaphoreExample {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(12);

    for (int i = 0; i < 12; i++) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          Downloader.INSTANCE.Download();
        }
      });
    }
  }

}
