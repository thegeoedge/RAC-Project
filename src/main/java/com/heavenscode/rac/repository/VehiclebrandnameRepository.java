package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Vehiclebrandname;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Vehiclebrandname entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehiclebrandnameRepository extends JpaRepository<Vehiclebrandname, Long> {}
