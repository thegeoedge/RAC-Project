package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Billingserviceoption;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Billingserviceoption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingserviceoptionRepository extends JpaRepository<Billingserviceoption, Long> {}
