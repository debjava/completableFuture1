package com.ddlab.rnd.type2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class WhencComplete1 {
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

  public static void main(String[] args) {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1());
    CompletableFuture<String> cf =
        cf1.whenComplete(
            (result, error) -> {
              // Perform some operations
              System.out.println("------ Performing intermediate operation -------");
              System.out.println("result= " + result);
              System.out.println("error= " + error);
            });
    String value = cf.join();
    System.out.println("Final value = " + value);
  }
}
