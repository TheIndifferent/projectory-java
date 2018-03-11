package com.github.hamrin.projectory.equation;

import com.google.common.collect.ImmutableList;
import com.github.hamrin.projectory.model.Equation;
import java.util.Random;

public class AdditionOperation implements Operation {

  private final Random rnd = new Random();

  @Override
  public Equation generateEquation() {
    int result = rnd.nextInt(12) + 3;
    int x = rnd.nextInt(result - 2) + 2;
    int y = result - x;
    return new Equation(ImmutableList.of(String.format("%d + %d", x, y)), result);
  }
}
