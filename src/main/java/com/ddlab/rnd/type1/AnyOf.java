package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AnyOf {
  private static void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String validateAadharNo(String aadharNo) {
    System.out.println("Started Aadhar validation ...");
    sleep(7);
    return "Your Aadhar No AA-12345 is valid";
  }

  public static String validatePanNo(String panNo) {
    System.out.println("Started Pan No validation ...");
    sleep(5);
    return "Your Pan No PN-1277 is valid";
  }

  public static String validatePassportNo(String passportNo) {
    System.out.println("Started Passport validation ...");
    sleep(5);
    return "Your Passport No PP-99 is valid";
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> validateAadharNo("12345"));
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> validatePanNo("12345"));
    CompletableFuture<String> cf3 =
        CompletableFuture.supplyAsync(() -> validatePassportNo("12345"));

    CompletableFuture<Object> cf4 = CompletableFuture.anyOf(cf1, cf2, cf3);
    cf4.thenAccept(s -> System.out.println("Intermediate value = " + s));
    Object obj = cf4.get();
    System.out.println("Now result : " + obj.toString());
  }
}
