package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocarejobinimages;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocarejobinimages entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocarejobinimagesRepository extends JpaRepository<Autocarejobinimages, Long> {}
