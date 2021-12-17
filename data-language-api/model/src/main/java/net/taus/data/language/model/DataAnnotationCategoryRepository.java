package net.taus.data.language.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DataAnnotationCategoryRepository extends JpaRepository<DataAnnotationCategory, Long>, DataAnnotationCategoryRepositoryFragment {
}

interface DataAnnotationCategoryRepositoryFragment {
  List<net.taus.data.language.api.model.DataAnnotationCategory> findAllSummaryBy();
}