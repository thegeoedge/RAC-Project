package com.heavenscode.rac.repository;

import com.heavenscode.rac.domain.EmpJobcommission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EmpJobcommission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpJobcommissionRepository extends JpaRepository<EmpJobcommission, Long> {}
