package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThenApply1 {
  public static String getInfo1() {
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "info-1";
  }

  public static CompletableFuture<String> task1() {
    CompletableFuture<String> cf =
        CompletableFuture.supplyAsync(
            () -> {
              return getInfo1();
            });
    return cf;
  }

  public static void main(String[] args) {
    CompletableFuture<String> cf1 = task1();
    CompletableFuture<String> cf2 =
        cf1.thenApply(
                value -> {
                  System.out.println("Actual Response : " + value);
                  return "Chain-1" + "-" + value;
                })
            .thenApply(
                val1 -> {
                  System.out.println("Now New Value : " + val1);
                  return "Chain-2" + "-" + val1;
                })
            .thenApply(
                val2 -> {
                  System.out.println("New Value in chain 3: " + val2);
                  return "Chain-3-" + val2;
                });

    String finalValue = null;
    try {
      finalValue = cf2.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Now final value :::" + finalValue);
  }
}
