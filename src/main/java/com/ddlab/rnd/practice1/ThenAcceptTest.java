package com.ddlab.rnd.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenAcceptTest {
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

  public static void main(String[] args) {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1());
    CompletableFuture<Void> cf =
        cf1.thenAccept(
            result -> {
              System.out.println("result = " + result);
              task2();
            });
    cf.join(); // If do not write it, task2() method will not be executed.
    String response = cf1.join();
    System.out.println("response = " + response);
  }
}
