package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocareappointmenttype;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocareappointmenttype entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocareappointmenttypeRepository extends JpaRepository<Autocareappointmenttype, Long> {}
