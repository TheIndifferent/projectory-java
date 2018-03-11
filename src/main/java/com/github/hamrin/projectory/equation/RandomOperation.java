package com.github.hamrin.projectory.equation;

import com.github.hamrin.projectory.model.Equation;
import com.google.common.collect.ImmutableList;
import java.util.Random;

public class RandomOperation implements Operation{

  private final Random rnd = new Random();
  private final ImmutableList<Operation> operations = ImmutableList.of(
      new AdditionOperation(),
      new SubstractionOperation(),
      new MultiplicationOperation(),
      new DivisionOperation(),
      new RomanAdditionOperation(),
      new RomanSubstractionOperation(),
      new EnglishAdditionOperation(),
      new EnglishMultiplicationOperation(),
      new EnglishSubstractionOperation()
  );

  @Override
  public Equation generateEquation() {
    return operations.get(rnd.nextInt(operations.size())).generateEquation();
  }
}
