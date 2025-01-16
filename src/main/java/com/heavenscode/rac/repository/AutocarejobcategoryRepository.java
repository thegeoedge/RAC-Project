package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autocarejobcategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autocarejobcategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutocarejobcategoryRepository extends JpaRepository<Autocarejobcategory, Long> {}
