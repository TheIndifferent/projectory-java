package com.github.hamrin.projectory.equation;

import static pl.allegro.finance.tradukisto.ValueConverters.ENGLISH_INTEGER;

import com.google.common.collect.ImmutableList;
import com.github.hamrin.projectory.model.Equation;
import java.util.Random;

public class EnglishAdditionOperation implements Operation {

  private final Random rnd = new Random();

  @Override
  public Equation generateEquation() {
    int result = rnd.nextInt(25) + 5;
    int x = rnd.nextInt(result - 4) + 4;
    int y = result - x;

    return new Equation(
        ImmutableList.of(
            ENGLISH_INTEGER.asWords(x),
            "plus",
            ENGLISH_INTEGER.asWords(y)),
        result);
  }
}
