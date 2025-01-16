package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Salesinvoice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Salesinvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesinvoiceRepository extends JpaRepository<Salesinvoice, Long> {}
