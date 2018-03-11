package com.github.hamrin.projectory.equation;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import com.github.hamrin.projectory.model.Equation;
import com.google.common.collect.ImmutableList;
import java.util.Random;

public class RomanAdditionOperation implements Operation {

  private final Random rnd = new Random();
  private final RomanNumeralFormat romanFormat = new RomanNumeralFormat();

  @Override
  public Equation generateEquation() {
    int result = rnd.nextInt(24) + 5;
    int x = rnd.nextInt(result - 4) + 4;
    int y = result - x;

    return new Equation(
        ImmutableList.of(
            romanFormat.format(x),
            "plus",
            romanFormat.format(y)),
        result);
  }
}
