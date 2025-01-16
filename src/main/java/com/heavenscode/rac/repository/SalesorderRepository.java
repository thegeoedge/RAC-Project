package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Salesorder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Salesorder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesorderRepository extends JpaRepository<Salesorder, Long> {}
