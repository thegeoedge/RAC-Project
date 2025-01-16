package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocaretimetable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocaretimetable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocaretimetableRepository extends JpaRepository<Autocaretimetable, Long> {}
