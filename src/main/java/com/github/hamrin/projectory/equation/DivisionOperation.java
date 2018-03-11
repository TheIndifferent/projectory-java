package com.github.hamrin.projectory.equation;

import com.google.common.collect.ImmutableList;
import com.github.hamrin.projectory.model.Equation;
import java.util.Random;

public class DivisionOperation implements Operation {

  private final Random rnd = new Random();

  @Override
  public Equation generateEquation() {
    int result = rnd.nextInt(100) + 1;
    int y = rnd.nextInt(50) + 1;
    int x = result * y;
    return new Equation(ImmutableList.of(String.format("%d / %d", x, y)), result);
  }
}
