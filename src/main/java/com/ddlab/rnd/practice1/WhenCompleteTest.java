package com.ddlab.rnd.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class WhenCompleteTest {
  private static void sleep() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String task1() {
    System.out.println("Task1 started running ...");
    sleep();
    System.out.println("Task1 completed");
    return "task1";
  }

  public static String task2() {
    String s = null;
    System.out.println("Task2 started running ...");
    sleep();
    if (s == null) throw new NullPointerException("A deliberate exception ...");
    System.out.println("Task2 completed");
    return "task2";
  }

  public static void main(String[] args) {
    CompletableFuture<String> cf1 =
        CompletableFuture.supplyAsync(() -> task1())
            .whenComplete(
                (result, error) -> {
                  System.out.println("Result : " + result);
                  System.out.println("Error : " + error);
                });
    String finalResult = cf1.join();
    System.out.println("Final Result : " + finalResult);

    CompletableFuture<String> cf2 =
        CompletableFuture.supplyAsync(() -> task2())
            .whenComplete(
                (result1, error1) -> {
                  System.out.println("Result1 : " + result1);
                  System.out.println("Error1 : " + error1.getMessage());
                });
    String finalResponse = cf2.join();
    System.out.println("finalResponse = " + finalResponse);
  }
}
