package net.taus.data.language.model.meta;

import net.taus.data.language.model.TranslationMetadataOrigin;
import net.taus.data.language.model.meta.Domain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DomainRepository extends CrudRepository<Domain, Long> {

  @Query("select new net.taus.data.language.api.model.Domain(d.id, d.description) from Domain d where d.origin = :origin")
  Iterable<net.taus.data.language.api.model.Domain> findByOrigin(@Param("origin") TranslationMetadataOrigin origin);

  List<Domain> findAllByIdIn(final List<Long> ids);
}
