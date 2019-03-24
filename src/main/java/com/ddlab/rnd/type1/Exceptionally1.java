// Usage of exceptionally
package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Exceptionally1 {

  public static String getValue1(String inParam1) {
    try {
      System.out.println("Get value -1 thread started running");
      TimeUnit.SECONDS.sleep(5);
      for (int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Thread-1 running ...");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "outParam1";
  }

  public static String getValue2(String inParam2) {
    try {
      System.out.println("Get value -2 thread started running");
      TimeUnit.SECONDS.sleep(2);
      for (int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Thread-2 running ...");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (inParam2 == null) throw new NullPointerException("Input parameter is null or blank ...");
    return "outParam2";
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> getValue1("Input1"));
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> getValue2(null));
    CompletableFuture<String> fallback1 = cf1.exceptionally(x -> "default outParam1");
    CompletableFuture<String> fallback2 = cf2.exceptionally(x -> "default outParam2");
    System.out.println("Fallback 1 :::" + fallback1.get());//outParam1
    System.out.println("Fallback 2 :::" + fallback2.get());//default outParam2
  }
}
