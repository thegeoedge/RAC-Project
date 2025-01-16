package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Autojobsinvoice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Autojobsinvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutojobsinvoiceRepository extends JpaRepository<Autojobsinvoice, Long> {}
