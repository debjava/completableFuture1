package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThenApplyWithException1 {
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
    CompletableFuture<String> chainCf =
        cf1.thenApply(
                returnValue1 -> {
                  System.out.println("First value : " + returnValue1);
                  return "Chain-1";
                })
            .thenApply(
                returnValue2 -> {
                  String str1 = returnValue2;
                  System.out.println("Chain-1 return value : " + returnValue2);
                  str1 = null;
                  if (str1 == null)
                    throw new NullPointerException("An unknown exception thrown...");
                  return "Chain-2";
                })
            .thenApply(
                returnValue3 -> {
                  System.out.println("Chain-3 response : " + returnValue3);
                  return "Chain-3";
                })
            .exceptionally(
                someValue -> {
                  System.out.println("someValue = " + someValue);
                  return "Default Fallback value";
                });
    try {
      String finalResponse = chainCf.get();
      System.out.println("Final Response : " + finalResponse);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
