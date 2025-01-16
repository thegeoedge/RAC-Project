package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocarehoist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocarehoist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocarehoistRepository extends JpaRepository<Autocarehoist, Long> {}
