package com.github.hamrin.projectory.equation;

import com.google.common.collect.ImmutableList;
import com.github.hamrin.projectory.model.Equation;
import java.util.Random;

public class MultiplicationOperation implements Operation {

  private final Random rnd = new Random();

  @Override
  public Equation generateEquation() {
    int r = rnd.nextInt(95) + 5;
    int x = rnd.nextInt(Math.max(r / 3 - 2, 1)) + 2;
    int y = r / x;
    int result = x * y;
    return new Equation(ImmutableList.of(String.format("%d * %d", x, y)), result);
  }
}
