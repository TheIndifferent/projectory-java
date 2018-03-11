package com.github.hamrin.projectory.equation;

import com.google.common.collect.ImmutableList;
import com.github.hamrin.projectory.model.Equation;
import java.util.Random;

public class SubstractionOperation implements Operation {

  private final Random rnd = new Random();

  @Override
  public Equation generateEquation() {
    int result = rnd.nextInt(30) + 1;
    int x = rnd.nextInt(150 - result) + 1 + result;
    int y = x - result;
    return new Equation(ImmutableList.of(String.format("%d - %d", x, y)), result);
  }
}
