package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Companybankaccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Companybankaccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanybankaccountRepository extends JpaRepository<Companybankaccount, Long> {}
