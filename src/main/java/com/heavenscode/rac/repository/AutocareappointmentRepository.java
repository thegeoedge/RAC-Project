package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocareappointment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocareappointment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocareappointmentRepository
    extends JpaRepository<Autocareappointment, Long>, JpaSpecificationExecutor<Autocareappointment> {}
