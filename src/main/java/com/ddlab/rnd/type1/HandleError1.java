package com.ddlab.rnd.type1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class HandleError1 {
  private static void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String performValidation(String someValue) {
    System.out.println("Started validation ...");
    sleep(5);
    if (someValue == null)
      throw new IllegalArgumentException("Parameter cannot be null or empty ...");
    return "Your value is " + someValue;
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<String> cf1 =
        CompletableFuture.supplyAsync(() -> performValidation(null))
            .handle(
                (response, error) -> {
                  System.out.println("Actual Response : " + response);
                  if (error != null) {
                    System.out.println("Actual Error : " + error.getMessage());
                    return "Since there is an error, I am sending default value.";
                  }
                  return response;
                });
    System.out.println("Now result : " + cf1.get());
  }
}
