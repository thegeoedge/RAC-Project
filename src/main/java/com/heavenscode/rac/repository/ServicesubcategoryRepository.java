package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Servicesubcategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Servicesubcategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicesubcategoryRepository extends JpaRepository<Servicesubcategory, Long> {}
