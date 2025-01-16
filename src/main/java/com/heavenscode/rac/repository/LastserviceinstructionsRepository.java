package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Lastserviceinstructions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Lastserviceinstructions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LastserviceinstructionsRepository extends JpaRepository<Lastserviceinstructions, Long> {}
