package com.github.hamrin.projectory.equation;

import static pl.allegro.finance.tradukisto.ValueConverters.ENGLISH_INTEGER;

import com.google.common.collect.ImmutableList;
import com.github.hamrin.projectory.model.Equation;
import java.util.Random;

public class EnglishMultiplicationOperation implements Operation {

  private final Random rnd = new Random();

  @Override
  public Equation generateEquation() {
    int r = rnd.nextInt(97) + 3;
    int x = rnd.nextInt(r / 2) + 2;
    int y = r / x;
    int result = x * y;

    return new Equation(
        ImmutableList.of(
            ENGLISH_INTEGER.asWords(x),
            "times",
            ENGLISH_INTEGER.asWords(y)),
        result);
  }

}
