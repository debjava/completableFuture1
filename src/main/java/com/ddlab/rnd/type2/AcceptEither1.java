package com.ddlab.rnd.type2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AcceptEither1 {
  private static void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String task1() {
    System.out.println("Started executing task-1");
    sleep(5);
    System.out.println("task-1 completed");
    return "task-1";
  }

  public static String task2() {
    System.out.println("Started executing task-2");
    sleep(3);
    System.out.println("task-2 completed");
    return "task-2";
  }

  public static void main(String[] args) {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1());
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> task2());
    CompletableFuture<Void> cf =
        cf1.acceptEither(
            cf2,
            a -> {
              System.out.println("Result = " + a);
            });
    cf.join();
  }
}
