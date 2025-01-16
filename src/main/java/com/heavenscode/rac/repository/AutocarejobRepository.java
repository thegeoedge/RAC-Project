package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocarejob;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocarejob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocarejobRepository extends JpaRepository<Autocarejob, Long> {}
