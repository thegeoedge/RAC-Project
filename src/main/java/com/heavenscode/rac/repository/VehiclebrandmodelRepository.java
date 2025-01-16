package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Vehiclebrandmodel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Vehiclebrandmodel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehiclebrandmodelRepository extends JpaRepository<Vehiclebrandmodel, Long> {}
