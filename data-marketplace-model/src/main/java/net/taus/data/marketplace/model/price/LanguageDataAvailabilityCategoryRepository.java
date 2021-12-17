package net.taus.data.marketplace.model.price;

import net.taus.data.marketplace.model.price.views.LanguageDataAvailabilityCategorySummary;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageDataAvailabilityCategoryRepository extends JpaRepository<LanguageDataAvailabilityCategory, Long> {
  LanguageDataAvailabilityCategory findFirstByLabel(String label);

  LanguageDataAvailabilityCategory findFirstByHigherBoundGreaterThanEqualOrderBySortAsc(double wordCount);

  Iterable<LanguageDataAvailabilityCategorySummary> findBy();

  default List<LanguageDataAvailabilityCategory> updateHigherBounds(final List<LanguageDataAvailabilityCount> counts) {
    var distribution = new Percentile();
    var distCounts = counts.stream().mapToDouble(LanguageDataAvailabilityCount::getWordCount).toArray();
    distribution.setData(distCounts);
    List<LanguageDataAvailabilityCategory> categories = findAll();
    for (var category : categories) {
      category.setHigherBound(distribution.evaluate(category.getHigherBoundPercentile()));
    }
    return saveAll(categories);
  }
}