package net.taus.data.marketplace.model.publication;

import net.taus.data.marketplace.model.publication.views.PublicationSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface PublicationRepository extends JpaRepository<Publication, Long>, PublicationRepositoryView {

  @Query("select p from Publication p left join fetch p.events where p.uid = :uid")
  Optional<Publication> findByUid(@Param("uid") final UUID uid);

  @Query("select p from Publication p left join fetch p.events where p.uid = :uid")
  Publication getOneByUid(@Param("uid") final UUID uid);

  @Query("select p from Publication p left join fetch p.events where p.documentUid = :documentUid")
  Optional<Publication> findByDocumentUid(@Param("documentUid") final UUID documentUid);

}

interface PublicationRepositoryView {

  Optional<PublicationSummary> findSummaryByUid(@Param("uid") final UUID uid);

}
