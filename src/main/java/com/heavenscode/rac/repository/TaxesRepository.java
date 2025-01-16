package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Taxes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Taxes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxesRepository extends JpaRepository<Taxes, Long> {}
