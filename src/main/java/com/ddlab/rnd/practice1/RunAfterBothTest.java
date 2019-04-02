package com.ddlab.rnd.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RunAfterBothTest {
  private static void sleep() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String task1() {
    System.out.println("Task1 started running ...");
    sleep();
    System.out.println("Task1 completed");
    return "task1";
  }

  public static String task2() {
    System.out.println("Task2 started running ...");
    sleep();
    System.out.println("Task2 completed");
    return "task2";
  }

  public static String task3() {
    System.out.println("Task3 started running ...");
    sleep();
    System.out.println("Task3 completed");
    return "task3";
  }

  public static void main(String[] args) {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1());
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> task2());
    CompletableFuture<Void> cf = cf1.runAfterBoth(cf2, () -> task3());
    cf.join();
  }
}
