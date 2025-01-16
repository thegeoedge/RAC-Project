package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Stocklocations;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Stocklocations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StocklocationsRepository extends JpaRepository<Stocklocations, Long> {}
