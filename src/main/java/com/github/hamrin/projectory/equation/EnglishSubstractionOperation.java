package com.github.hamrin.projectory.equation;

import static pl.allegro.finance.tradukisto.ValueConverters.ENGLISH_INTEGER;

import com.github.hamrin.projectory.model.Equation;
import com.google.common.collect.ImmutableList;
import java.util.Random;

public class EnglishSubstractionOperation implements Operation {

  private final Random rnd = new Random();

  @Override
  public Equation generateEquation() {
    int result = rnd.nextInt(30) + 1;
    int x = rnd.nextInt(99 - result) + 1 + result;
    int y = x - result;

    return new Equation(
        ImmutableList.of(
            ENGLISH_INTEGER.asWords(x),
            "minus",
            ENGLISH_INTEGER.asWords(y)),
        result);
  }

}
