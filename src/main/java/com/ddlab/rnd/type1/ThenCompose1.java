package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenCompose1 {
  public static String task1(String inParam1) {
    try {
      System.out.println("Task 1 thread started running");
      TimeUnit.SECONDS.sleep(5);
      for (int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Task Thread-1 running ...");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "outParam1";
  }

  public static String task2(String inParam2) {
    try {
      System.out.println("Get value -2 thread started running");
      TimeUnit.SECONDS.sleep(2);
      for (int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Task Thread-2 running ...");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (inParam2 == null) throw new NullPointerException("Input parameter is null or blank ...");
    return "outParam2";
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> task1("Input1"));
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> task2("Input2"));

    CompletableFuture<String> cf3 = cf1.thenCompose(s -> cf2);
    System.out.println(cf3.get());
  }
}
