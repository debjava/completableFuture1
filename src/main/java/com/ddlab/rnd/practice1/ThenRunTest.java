package com.ddlab.rnd.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenRunTest {

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
    CompletableFuture<Void> cfVoid = cf1.thenRun(() -> task2());
    cfVoid
        .join(); // If you do not write it, task2() will not be executed completely, only one line
                 // will be executed.
    String result = cf1.join();
    System.out.println("Final Result : " + result);
  }
}
