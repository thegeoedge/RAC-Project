package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Vehiclecategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Vehiclecategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehiclecategoryRepository extends JpaRepository<Vehiclecategory, Long> {}
