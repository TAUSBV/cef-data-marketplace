package net.taus.data.language;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import net.taus.data.language.model.TranslationUnitFinder;
import net.taus.data.language.model.search.LanguageDataSampler;
import net.taus.data.language.model.search.LanguageDataSearcher;
import net.taus.data.language.model.search.LanguageDataStatistician;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Arrays;

public interface Model {

  @Configuration
  @EntityScan(basePackageClasses = { Model.class })
  @EnableJpaRepositories(repositoryImplementationPostfix = "Extension")
  @EnableJpaAuditing
  @EnableCaching
  class ModelConfiguration extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
      SimpleCacheManager cacheManager = new SimpleCacheManager();
      cacheManager.setCaches(Arrays.asList(
        new ConcurrentMapCache("LanguageRepository.languageByTag"),
        new ConcurrentMapCache("TranslationVariantRepository.findByLanguageIsIn"),
        new ConcurrentMapCache("TranslationUnitRepository.findFlatSummaryBySourceAndTargetLanguage")
      ));
      return cacheManager;
    }

    @Bean
    public TranslationUnitFinder translationUnitFinder(final SQLQueryFactory sqlQueryFactory) {
      return new TranslationUnitFinder(sqlQueryFactory);
    }

    @Bean
    public com.querydsl.sql.Configuration querydslConfiguration() {
      SQLTemplates templates = MySQLTemplates.builder().build();
      com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
      configuration.setExceptionTranslator(new SpringExceptionTranslator());
      configuration.register("user", "createdAt", LocalDateTime.class);
      configuration.registerType("timestamp", LocalDateTime.class);
      configuration.setExceptionTranslator(new SpringExceptionTranslator());
      return configuration;
    }

    @Bean
    public SQLQueryFactory sqlQueryFactory(final DataSource dataSource) {
      Provider<Connection> provider = new SpringConnectionProvider(dataSource);
      return new SQLQueryFactory(querydslConfiguration(), provider);
    }

    @Bean
    public LanguageDataStatistician languageDataStatistician(final EntityManager manager) {
      return new LanguageDataStatistician(manager);
    }

    @Bean
    public LanguageDataSampler languageDataSampler(final SQLQueryFactory sqlQueryFactory) {
      return new LanguageDataSampler(sqlQueryFactory);
    }

    @Bean
    public LanguageDataSearcher languageDataExplorer(final EntityManager manager,
                                                     final LanguageDataStatistician statistician,
                                                     final LanguageDataSampler sampler) {
      return new LanguageDataSearcher(manager, statistician, sampler);
    }

  }

}
