package com.github.hamrin.projectory.equation;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import com.google.common.collect.ImmutableList;
import com.github.hamrin.projectory.model.Equation;
import java.util.Random;

public class RomanSubstractionOperation implements Operation {

  private final Random rnd = new Random();
  private final RomanNumeralFormat romanFormat = new RomanNumeralFormat();

  @Override
  public Equation generateEquation() {
    int result = rnd.nextInt(50) + 1;
    int x = rnd.nextInt(200 - result) + 1 + result;
    int y = x - result;
    return new Equation(
        ImmutableList.of(
            romanFormat.format(x),
            "minus",
            romanFormat.format(y)),
        result);
  }
}
