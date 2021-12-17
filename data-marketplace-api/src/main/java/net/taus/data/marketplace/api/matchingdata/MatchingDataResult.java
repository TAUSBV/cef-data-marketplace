package net.taus.data.marketplace.api.matchingdata;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Value
public class MatchingDataResult {
  List<DocumentSummary> documentSummaries;
  List<Selection> selections;
  List<String[]> samples;

  MatchingDataResult() {
    this.documentSummaries = new ArrayList<>();
    this.selections = new ArrayList<>();
    this.samples = new ArrayList<>();
  }

  public long getTotalSegmentCount() {
    return documentSummaries.stream().mapToLong(DocumentSummary::getSegmentCount).sum();
  }

  public long getTotalSourceWordCount() {
    return documentSummaries.stream().mapToLong(DocumentSummary::getSourceWordCount).sum();
  }

  public long getTotalTargetWordCount() {
    return documentSummaries.stream().mapToLong(DocumentSummary::getTargetWordCount).sum();
  }

  public List<Selection> getSelections(double amount) {
    var threshold = Math.round(getTotalSourceWordCount() * amount);
    final AtomicLong totalWordsParsed = new AtomicLong();
    return selections.stream().takeWhile(selection -> totalWordsParsed.getAndAdd(selection.sourceWordCount) < threshold).collect(Collectors.toList());
  }

  @Value
  public static class DocumentSummary {
    UUID documentUid;
    long segmentCount;
    long sourceWordCount;
    long targetWordCount;
  }

  @Value
  public static class Selection {
    UUID documentUid;
    long translationUnitId;
    long sourceWordCount;
    long targetWordCount;
  }
}
