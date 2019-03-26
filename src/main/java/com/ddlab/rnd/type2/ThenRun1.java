package com.ddlab.rnd.type2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenRun1 {
  private static void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String task1() {
    System.out.println("Started executing task-1");
    sleep(3);
    return "task-1";
  }

  public static String task2() {
    System.out.println("Started executing task-2");
    sleep(5);
    return "task-2";
  }

  public static void main(String[] args) {
    CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> task1())
            .thenRun(() -> task2());
    Void blank = cf.join();

    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1());
    String value = cf1.join();
    System.out.println("value = " + value);
  }
}
