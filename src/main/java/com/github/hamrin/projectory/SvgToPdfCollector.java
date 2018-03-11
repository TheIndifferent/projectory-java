package com.github.hamrin.projectory;

import static org.apache.batik.transcoder.SVGAbstractTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER;

import com.google.common.collect.ImmutableList;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class SvgToPdfCollector {

  private final ImmutableList<String> svgPages;
  private final PDDocument doc = new PDDocument(MemoryUsageSetting.setupTempFileOnly());

  public SvgToPdfCollector(ImmutableList<String> svgPages) {
    this.svgPages = svgPages;
  }

  public void writePdf(final String fileName) throws Exception {
    try {

      svgPages.stream()
          .map(this::renderPng)
          .map(this::pdfImage)
          .forEach(this::addPage);

      doc.save(fileName);

    } finally {
      doc.close();
    }
  }

  private void addPage(final PDImageXObject image) {
    try {
      final PDPage page = new PDPage(PDRectangle.A4);
      doc.addPage(page);
      PDPageContentStream contents = new PDPageContentStream(doc, page);
      contents.drawImage(image, 0, 0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
      contents.close();
    } catch (final RuntimeException rex) {
      throw rex;
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private PDImageXObject pdfImage(final byte[] png) {
    try {
      return PDImageXObject.createFromByteArray(doc, png, "");
    } catch (final RuntimeException rex) {
      throw rex;
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private byte[] renderPng(final String svg) {
    try {
      final ByteArrayOutputStream out = new ByteArrayOutputStream();
      final PNGTranscoder transcoder = new PNGTranscoder();
      transcoder.addTranscodingHint(KEY_PIXEL_UNIT_TO_MILLIMETER, 25.4f / 300);
//      transcoder.addTranscodingHint(KEY_WIDTH, PDRectangle.A4.getWidth());
//      transcoder.addTranscodingHint(KEY_HEIGHT, PDRectangle.A4.getHeight());
      transcoder.transcode(
          new TranscoderInput(new StringReader(svg)),
          new TranscoderOutput(out)
      );
      return out.toByteArray();
    } catch (final RuntimeException rex) {
      throw rex;
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}
