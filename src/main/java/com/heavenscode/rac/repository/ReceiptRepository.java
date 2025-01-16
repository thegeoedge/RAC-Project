package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Receipt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Receipt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {}
