package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Workshopvehiclework;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Workshopvehiclework entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkshopvehicleworkRepository extends JpaRepository<Workshopvehiclework, Long> {}
