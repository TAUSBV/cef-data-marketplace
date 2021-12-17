package net.taus.data.language.model.meta;

import net.taus.data.language.model.TranslationMetadataOrigin;
import net.taus.data.language.model.meta.ContentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentTypeRepository extends CrudRepository<ContentType, Long> {

  @Query("select new net.taus.data.language.api.model.ContentType(ct.id, ct.description) from ContentType ct where ct.origin = :origin")
  Iterable<net.taus.data.language.api.model.ContentType> findByOrigin(@Param("origin") TranslationMetadataOrigin origin);

  List<ContentType> findAllByIdIn(final List<Long> ids);

}
