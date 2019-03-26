package com.ddlab.rnd.type2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RunAfterBoth1 {
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
    System.out.println("task-1 completed");
    return "task-1";
  }

  public static String task2() {
    System.out.println("Started executing task-2");
    sleep(5);
    System.out.println("task-2 completed");
    return "task-2";
  }

  public static String task3() {
    System.out.println("Started executing task-3");
    sleep(5);
    System.out.println("task-3 completed");
    return "task-3";
  }

  public static void main(String[] args) {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1());
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> task2());
    // Task-3 will be started after the completion of task1 and task2
    CompletableFuture<Void> cf3 = cf1.runAfterBoth(cf2, () -> task3());
    cf3.join();
    String value = cf1.join();
    System.out.println("value = " + value);
  }
}
