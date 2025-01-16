package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.Hoisttype;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Hoisttype entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoisttypeRepository extends JpaRepository<Hoisttype, Long> {}
