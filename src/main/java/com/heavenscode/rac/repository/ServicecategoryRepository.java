package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Servicecategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Servicecategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicecategoryRepository extends JpaRepository<Servicecategory, Long> {}
