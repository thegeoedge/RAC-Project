package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Customervehicle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Customervehicle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomervehicleRepository extends JpaRepository<Customervehicle, Long>, JpaSpecificationExecutor<Customervehicle> {}
