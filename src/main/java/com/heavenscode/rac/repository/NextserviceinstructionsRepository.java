package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Nextserviceinstructions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Nextserviceinstructions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NextserviceinstructionsRepository extends JpaRepository<Nextserviceinstructions, Long> {}
