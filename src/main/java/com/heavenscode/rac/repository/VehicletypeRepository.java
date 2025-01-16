package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Vehicletype;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Vehicletype entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicletypeRepository extends JpaRepository<Vehicletype, Long> {}
