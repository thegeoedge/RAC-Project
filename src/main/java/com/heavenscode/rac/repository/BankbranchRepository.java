package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Bankbranch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Bankbranch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankbranchRepository extends JpaRepository<Bankbranch, Long> {}
