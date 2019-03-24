package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenCompose3 {

  private static void sleepNRun(String threadName, int time) {
    try {
      TimeUnit.SECONDS.sleep(time);
      for (int i = 0; i < 5; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Thread " + threadName + " is running");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static CompletableFuture<String> computeSomething() {
    return CompletableFuture.supplyAsync(
        () -> {
          sleepNRun("computeSomething", 5);
          return "test";
        });
  }

  public static CompletableFuture<Integer> computeInteger() {
    return CompletableFuture.supplyAsync(
        () -> {
          sleepNRun("computeInteger", 3);
          return 42;
        });
  }

  public static CompletableFuture<Boolean> computeBoolean() {
    return CompletableFuture.supplyAsync(
        () -> {
          sleepNRun("computeBoolean", 2);
          return false;
        });
  }

  public static CompletableFuture<Boolean> computeBoolean(Integer i) {
    return CompletableFuture.supplyAsync(
        () -> {
          sleepNRun("computeBoolean-Integer", 5);
          return i > 100000;
        });
  }

  public static CompletableFuture<Integer> computeInteger(int i) {
    return CompletableFuture.supplyAsync(
        () -> {
          sleepNRun("computeInteger-Inti", 4);
          return 42 + i;
        });
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<Integer> runResult =
        computeSomething()
            .thenCompose(
                s -> {
                  return computeInteger();
                });
    System.out.println("Now runResult :::" + runResult.get());
    CompletableFuture<Boolean> booleanResult =
        computeSomething()
            .thenCompose(s -> computeInteger())
            .thenCompose(i -> computeInteger(i))
            .thenCompose(b -> computeBoolean(b));
    System.out.println("booleanResult :::" + booleanResult.get());
  }
}
