package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Workshopworklist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Workshopworklist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkshopworklistRepository extends JpaRepository<Workshopworklist, Long> {}
