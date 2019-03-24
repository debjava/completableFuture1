package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AllOf1 {

  private static void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean validateAadharNo(String aadharNo) {
    System.out.println("Started Aadhar validation ...");
    sleep(7);
    return aadharNo == null ? false : true;
  }

  public static boolean validatePanNo(String panNo) {
    System.out.println("Started Pan No validation ...");
    sleep(5);
    return panNo == null ? false : true;
  }

  public static boolean validatePassportNo(String passportNo) {
    System.out.println("Started Passport validation ...");
    sleep(5);
    return passportNo == null ? false : true;
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<Boolean> cf1 = CompletableFuture.supplyAsync(() -> validateAadharNo("12345"));
    CompletableFuture<Boolean> cf2 = CompletableFuture.supplyAsync(() -> validatePanNo("12345"));
    CompletableFuture<Boolean> cf3 =
        CompletableFuture.supplyAsync(() -> validatePassportNo("12345"));

    CompletableFuture<Void> cf4 = CompletableFuture.allOf(cf1, cf2, cf3);
    cf4.get();
    System.out.println("Aadhar Validation : " + cf1.get());
    System.out.println("Pan No Validation : " + cf2.get());
    System.out.println("Passport Validation : " + cf3.get());
  }
}
