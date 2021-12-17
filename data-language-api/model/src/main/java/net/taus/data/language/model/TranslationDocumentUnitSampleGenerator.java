package net.taus.data.language.model;

import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import net.taus.data.language.model.sql.*;

import javax.transaction.Transactional;

import static com.querydsl.sql.SQLExpressions.select;
import static com.querydsl.sql.SQLExpressions.selectZero;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;

@Transactional
public class TranslationDocumentUnitSampleGenerator {

  private final SQLQueryFactory queryFactory;

  public TranslationDocumentUnitSampleGenerator(final SQLQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  public long generate(final TranslationDocument document, final int sampleSize) {
    if (!PUBLISHED.equals(document.getStatus())) {
      throw new TranslationDocumentException("Sample can only be generated for published documents.");
    }
    final QSQLTranslationDocuments td = new QSQLTranslationDocuments("td");
    final QSQLTranslationUnits tu = new QSQLTranslationUnits("tu");
    final QSQLTranslationVariants ts = new QSQLTranslationVariants("ts");
    final QSQLTranslationVariants tt = new QSQLTranslationVariants("tt");
    final QSQLTranslationDocumentsCharacteristics tdc = new QSQLTranslationDocumentsCharacteristics("tdc");
    final QSQLLanguageDataCharacteristics ldc = new QSQLLanguageDataCharacteristics("ldc");
    final QSQLLanguageDataCharacteristics ldcMax = new QSQLLanguageDataCharacteristics("ldcMax");
    final QSQLLanguageDataCharacteristicsDomains ldcDomains = new QSQLLanguageDataCharacteristicsDomains("ldcDomains");
    final QSQLLanguageDataCharacteristicsContentTypes ldcContentTypes = new QSQLLanguageDataCharacteristicsContentTypes("ldcContentTypes");
    final QSQLDomains d = new QSQLDomains("d");
    final QSQLContentTypes ct = new QSQLContentTypes("ct");
    final QSQLTranslationDocumentUnitSamples samples = new QSQLTranslationDocumentUnitSamples("tds");
    final QSQLTranslationVariantsAnnotations tva = new QSQLTranslationVariantsAnnotations("tva");
    queryFactory.delete(samples).where(samples.documentId.eq(document.getId().intValue())).execute();

    final SQLQuery<Integer> characteristicsMax = select(ldcMax.id.max()).from(ldcMax).where(ldcMax.id.eq(tdc.characteristicId));
    final SQLQuery<Tuple> sample = select(
      td.ownerId,
      td.id,
      td.uid,
      tu.id,
      tu.uid,
      tu.hash,
      tu.sequence,
      d.id,
      d.description,
      ct.id,
      ct.description,
      ts.id,
      ts.hash,
      ts.value,
      ts.wordCount,
      ts.languageId,
      tt.id,
      tt.hash,
      tt.value,
      tt.wordCount,
      tt.languageId,
      td.createdAt
    )
      .from(tu)
      .innerJoin(td).on(tu.documentId.eq(td.id))
      .innerJoin(ts).on(tu.sourceId.eq(ts.id))
      .innerJoin(tt).on(tu.targetId.eq(tt.id))
      .innerJoin(tdc).on(tdc.documentId.eq(td.id))
      .innerJoin(ldc).on(tdc.characteristicId.eq(ldc.id).and(ldc.id.eq(characteristicsMax)))
      .innerJoin(ldcDomains).on(ldcDomains.characteristicsId.eq(ldc.id))
      .innerJoin(d).on(d.id.eq(ldcDomains.domainId))
      .innerJoin(ldcContentTypes).on(ldcContentTypes.characteristicId.eq(ldc.id))
      .innerJoin(ct).on(ct.id.eq(ldcContentTypes.contentTypeId))
      .where(td.id.eq(document.getId().intValue())
        .and(tu.referenceId.isNull())
        .and(tu.quality.eq(TranslationUnit.Quality.HIGH.name()))
        .and(selectZero().from(tva).where(tva.translationVariantId.eq(tu.sourceId)).notExists())
        .and(selectZero().from(tva).where(tva.translationVariantId.eq(tu.targetId)).notExists())
      )
      .orderBy(ts.wordCount.desc())
      .limit(sampleSize);
    return queryFactory.insert(samples).columns(
      samples.ownerId,
      samples.documentId,
      samples.documentUid,
      samples.unitId,
      samples.unitUid,
      samples.unitHash,
      samples.unitSequence,
      samples.unitDomainId,
      samples.unitDomainDescription,
      samples.unitContentTypeId,
      samples.unitContentTypeDescription,
      samples.sourceId,
      samples.sourceHash,
      samples.sourceValue,
      samples.sourceWordCount,
      samples.sourceLanguageId,
      samples.targetId,
      samples.targetHash,
      samples.targetValue,
      samples.targetWordCount,
      samples.targetLanguageId,
      samples.createdAt
    ).select(sample).execute();
  }
}
