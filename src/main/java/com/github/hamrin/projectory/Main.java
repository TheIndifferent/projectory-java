package com.github.hamrin.projectory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hamrin.projectory.equation.Operation;
import com.github.hamrin.projectory.model.ResultsPage;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Lists;
import com.github.hamrin.projectory.equation.RandomOperation;
import com.github.hamrin.projectory.model.Card;
import com.github.hamrin.projectory.model.Colour;
import com.github.hamrin.projectory.model.Equation;
import com.github.hamrin.projectory.model.Page;
import com.github.hamrin.projectory.model.Project;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

  private static final int CARDS_PER_PAGE = 16;
  private static final int CARDS_PER_ROW = 4;
  private static final int PAGES_PER_PROJECT = 3;

  public static void main(final String[] args) throws Exception {
    new Main().work();
  }

  private Configuration cfg;

  private void work() throws Exception {
    final Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
    cfg.setTemplateLoader(new ClassTemplateLoader(Main.class, "/"));
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);

    final ImmutableList<Card> cards = generateCards();
    final ImmutableList<String> svgPages = ImmutableList.<String>builder()
        .addAll(svgCardsPages(cards))
        .add(svgResultsPage(cards))
        .addAll(svgIncidentPages())
        .build();
    new SvgToPdfCollector(svgPages)
        .writePdf(
            "TheGame_"
            + DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss").format(LocalDateTime.now())
            + ".pdf");
  }

  private ImmutableList<String> svgCardsPages(final ImmutableList<Card> cards) throws Exception {
    final Template template = getTemplate("cards.template.ftl");
    return Lists.partition(cards, CARDS_PER_PAGE).stream()
        .map(list -> {
          final Card lastCard = list.get(list.size() - 1);
          return new Page(
              "cards_" + lastCard.getColour().name() + "_" + lastCard.getNum() / CARDS_PER_PAGE,
              Lists.partition(list, CARDS_PER_ROW).stream()
                  .map(ImmutableList::copyOf)
                  .collect(ImmutableList.toImmutableList()));
        })
        .map(page -> writePage(template, page))
        .collect(ImmutableList.toImmutableList());
  }

  private String svgResultsPage(final ImmutableList<Card> cards) throws Exception {
    final ImmutableList<Project> projects = Lists.partition(cards, CARDS_PER_PAGE * PAGES_PER_PROJECT)
        .stream()
        .map(list -> {
          final Card lastCard = list.get(list.size() - 1);
          return new Project(lastCard.getColour(), ImmutableList.copyOf(list));
        })
        .collect(ImmutableList.toImmutableList());
    final Template template = getTemplate("answers.template.ftl");
    return writePage(template, new ResultsPage(projects));
  }

  private ImmutableList<String> svgIncidentPages() throws Exception {
    return Lists.partition(generateIncidents(), CARDS_PER_PAGE)
        .stream()
        .map(pageOfCards ->
                 writePage(
                     getTemplate("incidents.template.ftl"),
                     new Page("",
                              Lists.partition(pageOfCards, CARDS_PER_ROW)
                                  .stream()
                                  .map(ImmutableList::copyOf)
                                  .collect(ImmutableList.toImmutableList()))))
        .collect(ImmutableList.toImmutableList());
  }

  private <T> String writePage(final Template template, final T root) {
    final StringWriter out = new StringWriter();
    try {
      template.process(root, out);
      return out.toString();
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private ImmutableList<Card> generateCards() {
    final Builder<Card> builder = ImmutableList.builder();
    for (Colour colour : Colour.values()) {
      int i = 0;
      for (int pageNum = 0; pageNum < PAGES_PER_PROJECT; pageNum++) {
        for (int num = 0; num < CARDS_PER_PAGE; num++) {
          i++;
          final Equation equation = generateEquation();
          final Card card = new Card(
              colour,
              i,
              equation.getLines(),
              equation.getResult());
          builder.add(card);
        }
      }
    }
    return builder.build();
  }

  private final Operation randomOperation = new RandomOperation();

  private Equation generateEquation() {
    return randomOperation.generateEquation();
  }

  private ImmutableList<Card> generateIncidents() {
    try {

      final File incidentsFile = new File("incidents.json");
      maybeCopyIncidentsTemplate(incidentsFile);
      final List<List<String>> lists = new ObjectMapper()
          .readValue(incidentsFile,
                     new TypeReference<List<List<String>>>() {
                     });

      return lists.stream()
          .map(list -> new Card(null,
                                0,
                                ImmutableList.copyOf(list),
                                0))
          .collect(ImmutableList.toImmutableList());

    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private void maybeCopyIncidentsTemplate(final File incidentsFile) throws Exception {
    if (!incidentsFile.exists()) {
      System.out.println("Incidents file was not found, generating default...");
      try (final InputStream in = getClass().getResourceAsStream("/incidents.json");
           final OutputStream out = new FileOutputStream(incidentsFile)) {
        final byte[] buffer = new byte[8192];
        int read;
        while ((read = in.read(buffer)) != -1) {
          out.write(buffer, 0, read);
        }
        out.flush();
      }
    }
  }

  private Template getTemplate(final String templateName) {
    if (cfg == null) {
      cfg = new Configuration(Configuration.VERSION_2_3_23);
      cfg.setTemplateLoader(new ClassTemplateLoader(Main.class, "/"));
      cfg.setDefaultEncoding("UTF-8");
      cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
      cfg.setLogTemplateExceptions(false);
    }
    try {
      return cfg.getTemplate(templateName);
    } catch (final RuntimeException rex) {
      throw rex;
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}
