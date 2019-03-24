package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenAccept1 {
  public static String getActualWork(String param1) {
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return param1 == null ? "work-1" : param1;
  }

  public static CompletableFuture<String> task1() {
    CompletableFuture<String> cf =
        CompletableFuture.supplyAsync(
            () -> {
              return getActualWork(null);
            });
    return cf;
  }

  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();
    CompletableFuture<Void> cf =
        CompletableFuture.completedFuture("thenAcceptAsync message")
            .thenAcceptAsync(s -> result.append(s));
    cf.join();
    System.out.println("Result :::" + result);

    CompletableFuture<String> cf1 = task1();
    cf1.thenAccept(value -> System.out.println("New Value : " + value));
    System.out.println("Now Response : " + cf1.get());
  }
}
