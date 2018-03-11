package com.github.hamrin.projectory.model;

import com.google.common.collect.ImmutableList;

public class ResultsPage {

  private final ImmutableList<Project> projects;

  public ResultsPage(ImmutableList<Project> projects) {
    this.projects = projects;
  }

  public ImmutableList<Project> getProjects() {
    return projects;
  }
}
