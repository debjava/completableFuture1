package com.ddlab.rnd.type2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenAcceptBoth1 {
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

  public static String task3() {
    System.out.println("Started executing task-3");
    sleep(3);
    return "task-3";
  }

  public static void main(String[] args) {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1());
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> task2());
    cf1.thenAcceptBoth(
        cf2,
        (result1, result2) -> {
          System.out.println("result1 = " + result1);
          System.out.println("result2 = " + result2);
        });
    String newValue = cf2.join();
    System.out.println("newValue = " + newValue);
  }
}
