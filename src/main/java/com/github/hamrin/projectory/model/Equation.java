package com.github.hamrin.projectory.model;

import com.google.common.collect.ImmutableList;

public class Equation {
  private final ImmutableList<String> lines;
  private final Integer result;

  public Equation(final ImmutableList<String> lines, final Integer result) {

    this.lines = lines;
    this.result = result;
  }

  public ImmutableList<String> getLines() {
    return lines;
  }

  public Integer getResult() {
    return result;
  }

}
