package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Accounts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Accounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {}
