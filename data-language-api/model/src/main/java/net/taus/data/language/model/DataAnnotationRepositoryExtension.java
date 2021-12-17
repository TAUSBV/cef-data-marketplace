package net.taus.data.language.model;

import com.querydsl.core.ResultTransformer;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.*;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import net.taus.data.language.api.model.DataAnnotation;
import net.taus.data.language.api.model.DataAnnotationCategoryCount;
import net.taus.data.language.api.model.TranslationUnitAnnotation;
import net.taus.data.language.model.sql.*;
import net.taus.data.language.model.util.UUIDUtil;

import java.util.*;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.sql.SQLExpressions.rowNumber;
import static com.querydsl.sql.SQLExpressions.select;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class DataAnnotationRepositoryExtension {

  private final SQLQueryFactory sqlQueryFactory;

  public DataAnnotationRepositoryExtension(final SQLQueryFactory sqlQueryFactory) {
    this.sqlQueryFactory = sqlQueryFactory;
  }

  @SuppressWarnings("unchecked")
  public List<DataAnnotationCategoryCount> getDocumentAnnotationSummaryByUid(final UUID documentUid) {
    final NumberPath<Integer> categoryId = Expressions.numberPath(Integer.class, "category_id");
    final StringPath categoryDescription = Expressions.stringPath("category_description");
    final StringPath categoryCode = Expressions.stringPath("category_code");
    final NumberPath<Long> sourceId = Expressions.numberPath(Long.class, "source_id");
    final StringPath sourceValue = Expressions.stringPath("sourceValue");
    final NumberPath<Long> sourceWordCount = Expressions.numberPath(Long.class, "source_word_count");
    final NumberPath<Long> targetId = Expressions.numberPath(Long.class, "target_id");
    final StringPath targetValue = Expressions.stringPath("targetValue");
    final NumberPath<Long> targetWordCount = Expressions.numberPath(Long.class, "target_word_count");
    final NumberPath<Long> unitId = Expressions.numberPath(Long.class, "unit_id");
    final NumberPath<Long> rowNum = Expressions.numberPath(Long.class, "row_num");
    final NumberPath<Long> totalSourcePath = Expressions.numberPath(Long.class, "total_source");
    final NumberPath<Long> totalTargetPath = Expressions.numberPath(Long.class, "total_target");
    final NumberPath<Long> total = Expressions.numberPath(Long.class, "total");
    final QSQLDataAnnotations da = new QSQLDataAnnotations("da");
    final QSQLTranslationVariantsAnnotations tva = new QSQLTranslationVariantsAnnotations("tva");
    final QSQLDataAnnotationsCategories dac = new QSQLDataAnnotationsCategories("dac");
    final QSQLDataAnnotationsCategories dac2 = new QSQLDataAnnotationsCategories("dac2");
    final QSQLTranslationVariants vs = new QSQLTranslationVariants("vs");
    final QSQLTranslationVariants vt = new QSQLTranslationVariants("vt");
    final QSQLTranslationUnits tu = new QSQLTranslationUnits("tu");
    final QSQLTranslationDocuments doc = new QSQLTranslationDocuments("doc");

    int errorCategoryId = 12;
    final byte[] documentUidBytes = UUIDUtil.toBytes(documentUid);
    final SimpleExpression<Long> totalSource = select(da.count()).from(da)
      .innerJoin(dac2).on(da.categoryId.eq(dac2.id))
      .innerJoin(tva).on(da.id.eq(tva.annotationsId))
      .innerJoin(vs).on(tva.translationVariantId.eq(vs.id))
      .innerJoin(tu).on(tu.sourceId.eq(vs.id))
      .innerJoin(doc).on(tu.documentId.eq(doc.id))
      .where(doc.uid.eq(documentUidBytes).and(dac2.id.ne(errorCategoryId)).and(dac2.id.eq(dac.id)))
      .as(totalSourcePath);

    final SimpleExpression<Long> totalTarget = select(da.count()).from(da)
      .innerJoin(dac2).on(da.categoryId.eq(dac2.id))
      .innerJoin(tva).on(da.id.eq(tva.annotationsId))
      .innerJoin(vt).on(tva.translationVariantId.eq(vt.id))
      .innerJoin(tu).on(tu.targetId.eq(vt.id))
      .innerJoin(doc).on(tu.documentId.eq(doc.id))
      .where(doc.uid.eq(documentUidBytes).and(dac.id.ne(errorCategoryId)).and(dac2.id.eq(dac.id)))
      .as(totalTargetPath);

    final NumberExpression<Long> totalSourceCase = new CaseBuilder().when(totalSourcePath.isNull()).then(0L)
      .otherwise(totalSourcePath);
    final NumberExpression<Long> totalTargetCase = new CaseBuilder().when(totalTargetPath.isNull()).then(0L)
      .otherwise(totalTargetPath);

    final Expression<?>[] expressions = {
      dac.id.as(categoryId),
      dac.description.as(categoryDescription),
      dac.code.as(categoryCode),
      tu.id.as(unitId),
      vs.id.as(sourceId),
      vs.value.as(sourceValue),
      vs.wordCount.as(sourceWordCount),
      vt.id.as(targetId),
      vt.value.as(targetValue),
      vt.wordCount.as(targetWordCount),
      rowNumber().over().partitionBy(dac.id).orderBy(da.createdAt).as(rowNum),
      totalSource,
      totalTarget
    };

    final Expression<?>[] outerExpressions = {
      categoryId,
      categoryDescription,
      categoryCode,
      unitId,
      sourceId,
      sourceValue,
      sourceWordCount,
      targetId,
      targetValue,
      targetWordCount,
      rowNum,
      totalSourceCase.add(totalTargetCase).as(total)
    };

    final SimpleExpression<Tuple> sourceAnnotatedUnits = select(expressions).from(da)
      .innerJoin(dac).on(da.categoryId.eq(dac.id))
      .innerJoin(tva).on(da.id.eq(tva.annotationsId))
      .innerJoin(vs).on(tva.translationVariantId.eq(vs.id))
      .innerJoin(tu).on(tu.sourceId.eq(vs.id))
      .innerJoin(vt).on(tu.targetId.eq(vt.id))
      .innerJoin(doc).on(tu.documentId.eq(doc.id))
      .where(doc.uid.eq(documentUidBytes).and(dac.id.ne(errorCategoryId)))
      .as("source_annotated");

    final SimpleExpression<Tuple> targetAnnotatedUnits = select(expressions).from(da)
      .innerJoin(dac).on(da.categoryId.eq(dac.id))
      .innerJoin(tva).on(da.id.eq(tva.annotationsId))
      .innerJoin(vt).on(tva.translationVariantId.eq(vt.id))
      .innerJoin(tu).on(tu.targetId.eq(vt.id))
      .innerJoin(vs).on(tu.sourceId.eq(vs.id))
      .innerJoin(doc).on(tu.documentId.eq(doc.id))
      .where(doc.uid.eq(documentUidBytes).and(dac.id.ne(errorCategoryId)))
      .as("target_annotated");

    final ResultTransformer<Map<DataAnnotationCategoryCount, List<TranslationUnitAnnotation>>> groupByCategories =
      groupBy(constructor(DataAnnotationCategoryCount.class, categoryId.longValue(), categoryCode, categoryDescription, total))
        .as(list(constructor(TranslationUnitAnnotation.class, unitId, sourceId, sourceValue, sourceWordCount, targetId, targetValue, targetWordCount)));

    final SQLQuery<Tuple> sub1 = select(outerExpressions).from(sourceAnnotatedUnits).where(rowNum.loe(totalSourcePath.divide(2).ceil()));
    final SQLQuery<Tuple> sub2 = select(outerExpressions).from(targetAnnotatedUnits).where(rowNum.loe(totalTargetPath.divide(2).ceil()));

    final Map<DataAnnotationCategoryCount, List<TranslationUnitAnnotation>> map = sqlQueryFactory
      .select().distinct().unionAll(Expressions.stringPath("annotations"), sub1, sub2).transform(groupByCategories);

    return map.entrySet().stream().map(e -> {
      final DataAnnotationCategoryCount category = e.getKey();
      final List<TranslationUnitAnnotation> units = e.getValue();
      category.setUnits(units);
      final Set<Long> ids = units.stream().map(TranslationUnitAnnotation::getSourceId).collect(toSet());
      ids.addAll(units.stream().map(TranslationUnitAnnotation::getTargetId).collect(toSet()));
      final Map<Long, List<DataAnnotation>> a = sqlQueryFactory
        .from(da)
        .innerJoin(dac).on(da.categoryId.eq(dac.id))
        .innerJoin(tva).on(da.id.eq(tva.annotationsId))
        .innerJoin(vs).on(tva.translationVariantId.eq(vs.id))
        .where(vs.id.in(ids))
        .transform(groupBy(vs.id).as(list(constructor(DataAnnotation.class,
          da.id.longValue(), da.offsetStart, da.offsetEnd, dac.id.longValue(), dac.description, dac.code, dac.kind))));
      units.forEach(u -> {
        u.setSourceAnnotations(a.getOrDefault(u.getSourceId(), new ArrayList<>()));
        u.setTargetAnnotations(a.getOrDefault(u.getTargetId(), new ArrayList<>()));
      });
      return category;
    }).collect(toList());
  }

}
