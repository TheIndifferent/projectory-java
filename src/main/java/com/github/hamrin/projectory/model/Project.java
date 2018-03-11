package com.github.hamrin.projectory.model;

import com.google.common.collect.ImmutableList;

public class Project {

  private final Colour colour;
  private final ImmutableList<Card> cards;

  public Project(Colour colour, ImmutableList<Card> cards) {
    this.colour = colour;
    this.cards = cards;
  }

  public Colour getColour() {
    return colour;
  }

  public ImmutableList<Card> getCards() {
    return cards;
  }
}
