package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenCompose2 {

  public static String getUserName(String userId) {
    try {
      System.out.println("User Name thread started running & user Id-" + userId);
      TimeUnit.SECONDS.sleep(5);
      for (int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("User Name running ...");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "John Abraham";
  }

  public static String getUserRating(String userName) {
    try {
      System.out.println("User Rating thread started running & user Name-" + userName);
      TimeUnit.SECONDS.sleep(1);
      for (int i = 0; i < 5; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("User Rating thread running ...");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (userName == null) throw new NullPointerException("Input parameter is null or blank ...");
    return "5 plus";
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> getUserName("ID-101"));
    CompletableFuture<String> cf3 =
        cf1.thenCompose(resultCF1 -> CompletableFuture.supplyAsync(() -> getUserRating(resultCF1)));
    System.out.println(cf3.get());//5 plus
  }
}
