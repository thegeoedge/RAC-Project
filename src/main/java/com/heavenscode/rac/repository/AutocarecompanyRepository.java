package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocarecompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocarecompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocarecompanyRepository extends JpaRepository<Autocarecompany, Long> {}
