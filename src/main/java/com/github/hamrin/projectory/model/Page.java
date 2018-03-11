package com.github.hamrin.projectory.model;

import com.google.common.collect.ImmutableList;

public class Page {

  private final String name;
  private final ImmutableList<ImmutableList<Card>> cardsRow;

  public Page(String name,
              ImmutableList<ImmutableList<Card>> cards) {
    this.name = name;
    this.cardsRow = cards;
  }

  public String getName() {
    return name;
  }

  public ImmutableList<ImmutableList<Card>> getCardsRow() {
    return cardsRow;
  }
}
