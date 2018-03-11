package com.github.hamrin.projectory.model;

import com.google.common.collect.ImmutableList;

public class Card {

  private final Colour colour;
  private final Integer num;
  private final ImmutableList<String> lines;
  private final Integer result;

  public Card(Colour colour,
              Integer num,
              ImmutableList<String> lines,
              Integer result) {
    this.colour = colour;
    this.num = num;
    this.lines = lines;
    this.result = result;
  }

  public Colour getColour() {
    return colour;
  }

  public ImmutableList<String> getLines() {
    return lines;
  }

  public Integer getResult() {
    return result;
  }

  public Integer getNum() {
    return num;
  }

}
