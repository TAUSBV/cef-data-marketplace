package net.taus.data.language.model;

import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import net.taus.data.language.model.meta.Language;
import net.taus.data.language.model.sql.QSQLTranslationVariants;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.core.types.dsl.Expressions.numberTemplate;
import static com.querydsl.core.types.dsl.Expressions.stringPath;

public class TranslationVariantRepositoryExtension implements TranslationVariantRepositoryCustom {

  private final SQLQueryFactory queryFactory;

  public TranslationVariantRepositoryExtension(final SQLQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  @Override
  @Cacheable(value = "TranslationVariantRepository.findByLanguageIsIn")
  public synchronized Map<Long, TranslationVariant> findByLanguageIsIn(final List<Language> languages, final long limit) {
    final QSQLTranslationVariants variant = new QSQLTranslationVariants("v");
    final Integer[] languageIds = languages.stream().mapToInt(language -> language.getId().intValue()).boxed().toArray(Integer[]::new);
    final SubQueryExpression<Long> sub = SQLExpressions.select(variant.hash).distinct()
      .from(variant)
      .where(variant.languageId.in(languageIds))
      .limit(limit);
    final SQLQuery<?> query = queryFactory.query()
      .from(variant)
      .join(sub, stringPath("sub")).on(variant.hash.in(numberTemplate(Long.class, "(sub.hash)")));
    return query.transform(groupBy(variant.hash).as(constructor(TranslationVariant.class, variant.id)));
  }
}
