package com.ddlab.rnd.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenApplyTest {
  private static void sleep() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String createBasicPizza() {
    System.out.println("Pizza creation started ...");
    sleep();
    System.out.println("Pizza creation completed");
    return "Pizza";
  }

  public static String addToppings() {
    System.out.println("Adding Toppings ...");
    sleep();
    System.out.println("Toppings added");
    return "Toppings";
  }

  public static String packPizza() {
    System.out.println("Adding pizza to a box ...");
    sleep();
    System.out.println("Pizza added to box");
    return "Pizza with Box";
  }

  public static void main(String[] args) {
    CompletableFuture<String> cf1 =
        CompletableFuture.supplyAsync(() -> createBasicPizza())
            .thenApply(
                resultPizza -> {
                  String toppings = addToppings();
                  return resultPizza + "-" + toppings;
                })
            .thenApply(
                resultToppingsPizza -> {
                  String pizzaBox = packPizza();
                  return resultToppingsPizza + "-" + pizzaBox;
                });
    String pizzaToEat = cf1.join();
    System.out.println("Pizza to eat : " + pizzaToEat);
  }
}
