package net.taus.data.language.model;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.taus.data.language.api.model.DataAnnotationCategory;

import javax.persistence.EntityManager;
import java.util.List;

public class DataAnnotationCategoryRepositoryExtension implements DataAnnotationCategoryRepositoryFragment {

  private final EntityManager entityManager;

  public DataAnnotationCategoryRepositoryExtension(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<DataAnnotationCategory> findAllSummaryBy() {
    final QDataAnnotationCategory annotationCategory = new QDataAnnotationCategory("dac");
    final JPAQueryFactory query = new JPAQueryFactory(entityManager);
    return query.from(annotationCategory)
      .select(Projections.constructor(
        DataAnnotationCategory.class,
        annotationCategory.id,
        annotationCategory.description,
        annotationCategory.code,
        annotationCategory.kind.stringValue()
        ))
      .fetch();
  }
}
