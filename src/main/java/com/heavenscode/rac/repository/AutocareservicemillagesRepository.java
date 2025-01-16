package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocareservicemillages;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocareservicemillages entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocareservicemillagesRepository extends JpaRepository<Autocareservicemillages, Long> {}
